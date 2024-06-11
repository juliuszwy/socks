package com.scoks.order.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 
 
public class Md5Util {

    private static final Logger logger = LoggerFactory.getLogger(Md5Util.class);

    public static String encryptMD5(String str) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        StringBuffer buf = new StringBuffer();
        md5.update(str.getBytes());
        byte[] bytes = md5.digest();

        for(int i = 0; i < bytes.length; ++i) {
            String s = Integer.toHexString(bytes[i] & 255);
            if (s.length() == 1) {
                buf.append("0");
            }

            buf.append(s);
        }

        return buf.toString();
    }

}