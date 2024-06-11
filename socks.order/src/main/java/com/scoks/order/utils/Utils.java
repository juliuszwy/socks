package com.scoks.order.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class Utils {

    public final static Pattern PATTERN_LOCAL_IP = Pattern.compile("(10|172|192)(\\.\\d{1,3}){3,5}$");
    public static String toDateString(Date date, String format) {
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        return fmt.format(date);
    }

    public static long toDate(String date, String format) {
        if(Utils.stringIsNullOrEmpty(date))return 0;
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        long time = 0l;
        try {
            Date parse = fmt.parse(date);
            time = parse.getTime();
        } catch (Exception e) {
        }
        return time;
    }
    public static boolean stringIsNullOrEmpty(String string) {
        return string == null || string.trim().length() < 1;
    }
    public static List<String> toListString(List<Object> objs) {
        if (objs == null)
            return null;

        List<String> list = new ArrayList<String>();
        for (Object item : objs)
            if (item == null)
                list.add(null);
            else
                list.add(item.toString());
        return list;
    }
    public static List<String> toListString(String string) {
        List<String> list = new ArrayList<String>();
        if (!Utils.stringIsNullOrEmpty(string)) {
            String[] lines = string.trim().split(",");
            for (String item : lines) {
                list.add(item);
            }
        }
        return list;
    }
    public static String[] toArrayString(List<String> list) {
        if (list != null) {
            return list.toArray(new String[list.size()]);
        }
        return null;
    }

    public static String[] toArrayString(String string) {
        return toArrayString(string, ",");
    }
    public static String[] toArrayString(String string, String regex) {
        if (Utils.stringIsNullOrEmpty(string))
            return null;
        return string.split(regex);
    }
    public static boolean stringCompare(String string1, String string2) {
        if (string1 == null) {
            if (string2 == null)
                return true;
            else
                return false;
        }

        return string1.equalsIgnoreCase(string2);
    }
    public static int toInt(Object data, int defaultValue) {
        try {
            if (data == null)
                return defaultValue;
            return Integer.valueOf(String.valueOf(data));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static int toInt(String data, int defaultValue) {
        try {
            return Integer.valueOf(data);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static String randomStr(int i) {

        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < i; j++) {
            //生成一个97-122之间的int类型整数--为了生成小写字母
            int intValL = (int) (Math.random() * 26 + 97);
            //生成一个65-90之间的int类型整数--为了生成大写字母
            int intValU = (int) (Math.random() * 26 + 65);
            //生成一个30-39之间的int类型整数--为了生成数字
            int intValN = (int) (Math.random() * 10 + 48);

            int intVal = 0;
            int r = (int) (Math.random() * 3);

            if (r == 0) {
                intVal = intValL;
            } else if (r == 1) {
                intVal = intValU;
            } else {
                intVal = intValN;
            }

            sb.append((char) intVal);
        }
        return sb.toString();
    }
}
