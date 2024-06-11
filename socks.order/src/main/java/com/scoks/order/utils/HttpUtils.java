package com.scoks.order.utils;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

public class HttpUtils {
	public enum HeaderProperty {
		Content_Type("Content-Type"), Connection("Connection"), User_Agent("User-Agent"), X_Forwarded_For(
				"x-forwarded-for"), Accept_Language("Accept-Language"), Refer("Refer"), Cookie("Cookie"), Response(
						"Response"), ConnectTimeout(
								"ConnectTimeout"), ReadTimeout("ReadTimeout"), Host("Host"), req_log("req_log"), Accept("Accept");

		private String value;

		private static TreeMap<Integer, HeaderProperty> _map;

		static {
			_map = new TreeMap<Integer, HeaderProperty>();
			int i = 0;
			for (HeaderProperty num : HeaderProperty.values()) {
				_map.put(new Integer(i++), num);
			}
		}

		public static HeaderProperty lookup(int value) {
			return _map.get(new Integer(value));
		}

		HeaderProperty(String value) {
			this.value = value;
		}

		public String value() {
			return this.value;
		}

		@Override
		public String toString() {
			return value();
		}
	}

	static Logger logger = Logger.getLogger(HttpUtils.class.getSimpleName());

	private static String getParams(HashMap<String, String> params, String charset)
			throws UnsupportedEncodingException {
		if (params != null) {
			StringBuilder sb = new StringBuilder();
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if (params.get(key) != null)
					sb.append(key + "=" + java.net.URLEncoder.encode(params.get(key), charset) + "&");
			}
			return sb.toString();
		}
		return null;
	}



	public static void main(String[] args) {

	}

	private static void setRequestProperty(HttpURLConnection conn, HashMap<HeaderProperty, String> header) {
		if (header == null) {
			conn.setRequestProperty(HeaderProperty.Content_Type.value(),
					"application/x-www-form-urlencoded; charset=utf-8");
			conn.setRequestProperty(HeaderProperty.Connection.value(), "Keep-Alive");
			conn.setRequestProperty(HeaderProperty.User_Agent.value(),
					"Mozilla/5.0 (Windows NT 5.1; rv:14.0) Gecko/20100101 Firefox/14.0.1");
			conn.setRequestProperty(HeaderProperty.Accept_Language.value(), "zh-CN");
		} else {

			Set<HeaderProperty> keys = header.keySet();
			for (HeaderProperty key : keys) {
				if (key == HeaderProperty.ConnectTimeout)
					conn.setConnectTimeout(Utils.toInt(header.get(key), 30000));
				else if (key == HeaderProperty.ReadTimeout)
					conn.setReadTimeout(Utils.toInt(header.get(key), 30000));
				else
					conn.setRequestProperty(key.value(), header.get(key));

			}

			if (!header.containsKey(HeaderProperty.Content_Type))
				conn.setRequestProperty(HeaderProperty.Content_Type.value(),
						"application/x-www-form-urlencoded; charset=utf-8");

			if (!header.containsKey(HeaderProperty.Connection))
				conn.setRequestProperty(HeaderProperty.Connection.value, "Keep-Alive");

			if (!header.containsKey(HeaderProperty.Accept_Language))
				conn.setRequestProperty(HeaderProperty.Accept_Language.value(), "zh-CN");

		}
	}

	public static String urlEncode(String str, String code) {
		try {
			return java.net.URLEncoder.encode(str, code);
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		}
		return null;
	}

	public static String urlDecode(String str, String code) {
		try {
			return java.net.URLDecoder.decode(str, code);
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		}
		return null;
	}

	public static String urlEncode(String str) {
		return urlEncode(str, "utf-8");
	}

	public static String urlDecode(String str) {
		return urlDecode(str, "utf-8");
	}
}