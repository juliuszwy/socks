package com.scoks.order.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.ui.ModelMap;

public class WebUtils {

	private static Logger logger = Logger.getLogger("WebUtil");
	public static final String RequestAttributeParamStr = "!@#paramValues";
	private static final String RequestAttributeFileItemStr = "!@#fileItem";
	private static final String RequestAttributeHeadParamStr = "param";
	public final static Pattern PATTERN_IP = Pattern.compile("(\\d{1,3})(\\.\\d{1,3}){3,5}$");
	private static ObjectMapper objectMapper = null;



	public static void getStaticHtml(HttpServletRequest req, HttpServletResponse resp, String file) throws IOException {
		resp.setContentType("text/html; charset=UTF-8");
		InputStream r = null;// req.getServletContext().getResourceAsStream(file);

		byte[] bs = new byte[32768];
		int length = 0;
		do {
			if (r != null) {
				length = r.read(bs);
				if (length > 0)
					resp.getOutputStream().write(bs, 0, length);
			}
		} while (length > 0);
	}

	public static void setAccessLog(HttpServletRequest req, String name, String value) {

		Map m = null;
		Object o = req.getAttribute("RESP_LOG");
		if (o != null && o instanceof Map)
			m = (Map) o;
		else {
			m = new HashMap<String, String>();
			req.setAttribute("RESP_LOG", m);
		}

		m.put(name, value);

	}

	private static final String STR_UNKNOW = "unknown";

	@Deprecated
	public static String getClietIp(HttpServletRequest request) {
		return getClientIp(request);
	}

	private static String gateWayIPStr = null;

	public static void setGateWayIPStr(String gateWayIPStr) {
		if (gateWayIPStr != null) {
			Map<String, String> tmp = new HashMap<String, String>();
			List<String> list = Utils.toListString(gateWayIPStr);
			for (String str : list) {
				tmp.put(str, str);
			}
			WebUtils.gateWayIPS = tmp;
		} else
			WebUtils.gateWayIPS = null;
	}

	private static Map<String, String> gateWayIPS = null;

	/**
	 * 经过代理以后，由于在客户端和服务之间增加了中间层，因此服务器无法直接拿到客户端的IP，<br>
	 * 服务器端应用也无法直接通过转发请求的地址返回给客户端。 但是在转发请求的HTTP头信息中，<br>
	 * 增加了X－FORWARDED－FOR信息用以跟踪原有的客户端IP地址和原来客户端请求的服务器地址。
	 * 原来如此，我们的项目中正好是有前置apache， 将一些请求转发给后端的weblogic，<br>
	 * 看来就是这样导致的。
	 * 
	 * 
	 * @param request
	 * @return 获取真实的IP地址
	 */
	public static String getClientIp(HttpServletRequest request) {
		String ip = null;
		if (request != null) {
			ip = request.getHeader("x-forwarded-for");
			if (Utils.stringIsNullOrEmpty(ip) || STR_UNKNOW.equalsIgnoreCase(ip))
				ip = request.getHeader("Proxy-Client-IP");
			if (Utils.stringIsNullOrEmpty(ip) || STR_UNKNOW.equalsIgnoreCase(ip))
				ip = request.getHeader("WL-Proxy-Client-IP");
			if (Utils.stringIsNullOrEmpty(ip) || STR_UNKNOW.equalsIgnoreCase(ip))
				ip = request.getHeader("X-Real-IP");
			if (Utils.stringIsNullOrEmpty(ip) || STR_UNKNOW.equalsIgnoreCase(ip))
				ip = request.getRemoteAddr();
		}

		return getClientIp(ip);
	}

	public static String getClientIp(String ip) {
		if (ip == null)
			return null;

		String[] ipArr = Utils.toArrayString(ip);
		if (ipArr == null || ipArr.length == 0)
			return null;

		if (ipArr.length == 1)
			return ipArr[0].trim();

		if (gateWayIPS != null && gateWayIPS.size() > 0) {
			int pos = 0;
			try {
				for (int i = 1; i < ipArr.length; i++) {
					String item = ipArr[i];
					item = item.trim();
					if (item == null || !PATTERN_IP.matcher(item).find())
						continue;

					if (gateWayIPS.containsKey(item)) {
						for (int j = i - 1; j >= 0; j--) {
							String tmp = ipArr[j];
							tmp = tmp.trim();
							if (tmp == null || !PATTERN_IP.matcher(tmp).find())
								continue;

							if (!Utils.stringCompare("127.0.0.1", tmp) && !Utils.PATTERN_LOCAL_IP.matcher(tmp).find()
									&& !gateWayIPS.containsKey(tmp))
								return tmp;
						}
					}
				}
			} catch (Exception ex) {
				logger.error(ip, ex);
			}
		}

		for (int i = 0; i < ipArr.length; i++) {
			String item = ipArr[i];
			item = item.trim();
			if (item == null || !PATTERN_IP.matcher(item).find())
				continue;

			if (!Utils.stringCompare("127.0.0.1", item) && !Utils.PATTERN_LOCAL_IP.matcher(item).find())
				return item;
		}

		return ipArr[0].trim();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WebUtils.setGateWayIPStr("123.207.123.109");
		System.out.println(WebUtils.getClientIp("192.168.0.1 ,123.207.123.109,  123.207.123.109 , 127.0.0.1"));
	}



}
