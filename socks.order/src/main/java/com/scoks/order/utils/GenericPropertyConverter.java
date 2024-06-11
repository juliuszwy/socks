package com.scoks.order.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.function.Function;

@Slf4j
public class GenericPropertyConverter {

    public static final char UNDERLINE_CHAR = '_';

    public static <T> T convertObject(Object originObj, PropertyNameStyle originStyle, Class<T> targetClass) {

        return baseConvertObject(originObj, originStyle, targetClass, null, null);
    }

    public static <T, F> List<T> convertObject(List<F> originLst, PropertyNameStyle originStyle, Class<T> targetClass) {
        if (Objects.isNull(originLst)) {
            return null;
        }
        List<T> result = new ArrayList<>(originLst.size());
        originLst.forEach(o -> result.add(convertObject(o, originStyle, targetClass)));
        return result;
    }

    /**
     * @param originObj                待转换的源对象，【不可为null】
     * @param originStyle              源对象的property的命名风格，【不可为null】
     * @param targetClass              目标对象，【不可为null】FieldNamingPolicy
     * @param customizePropertyNameMap 自定义转换属性，【可为null】
     * @param excludePropertyNameList  自定义的排除属性，【可为null】
     * @return
     */
    public static <T> T baseConvertObject(Object originObj, PropertyNameStyle originStyle, Class<T> targetClass,
                                          Map<String, String> customizePropertyNameMap, List<String> excludePropertyNameList) {

        T targetObj;
        try {
            targetObj = targetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {

            log.error("通用的风格属性名转换时出错\n{}", e.getMessage());
            return null;
        }

        Class<?> originClass = originObj.getClass();
        PropertyDescriptor[] originPds = BeanUtils.getPropertyDescriptors(originClass),
                targetPds = BeanUtils.getPropertyDescriptors(targetClass);
        Function<String, String> propertyConvertFunc = PropertyNameStyle.CAMEL.equals(originStyle)
                ? GenericPropertyConverter::camel2Underline
                : GenericPropertyConverter::underline2Camel;
        for (PropertyDescriptor originPd : originPds) {

            String propertyName = originPd.getName();
            if ("class".equals(propertyName)) {

                continue;
            }

            if (CollectionUtils.isNotEmpty(excludePropertyNameList) && excludePropertyNameList.contains(propertyName)) {

                continue;
            }

            String newPropertyName = null;
            if (MapUtils.isNotEmpty(customizePropertyNameMap)) {

                newPropertyName = customizePropertyNameMap.get(propertyName);
            }
            if (Utils.stringIsNullOrEmpty(newPropertyName)) {

                newPropertyName = propertyConvertFunc.apply(propertyName);
            }
            if (Utils.stringIsNullOrEmpty(newPropertyName)) {

                continue;
            }

            Class<?> originPropertyType = originPd.getPropertyType();
            for (PropertyDescriptor targetPd : targetPds) {

                if (newPropertyName.equals(targetPd.getName()) == false) {

                    continue;
                }

                Method originReadMethod = originPd.getReadMethod(),
                        targetWriteMethod = targetPd.getWriteMethod();
                if (originReadMethod != null && targetWriteMethod != null
                        && ClassUtils.isAssignable(targetWriteMethod.getParameterTypes()[0], originPropertyType)) {

                    try {
                        if (Modifier.isPublic(originReadMethod.getDeclaringClass().getModifiers()) == false) {

                            originReadMethod.setAccessible(true);
                        }
                        Object value = originReadMethod.invoke(originObj);
                        if (Modifier.isPublic(targetWriteMethod.getDeclaringClass().getModifiers()) == false) {

                            targetWriteMethod.setAccessible(true);
                        }
                        targetWriteMethod.invoke(targetObj, value);
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {

                        log.error("通用的风格属性名转换时出错\n{}", e1.getMessage());
                        return null;
                    }
                }
            }
        }
        return targetObj;
    }

    /**
     * 驼峰转下划线
     *
     * @param camelStr
     * @return
     */
    public static String camel2Underline(String camelStr) {

        if (Utils.stringIsNullOrEmpty(camelStr)) {

            return null;
        }

        int len = camelStr.length();
        StringBuilder strb = new StringBuilder(len + len >> 1);
        for (int i = 0; i < len; i++) {

            char c = camelStr.charAt(i);
            if (Character.isUpperCase(c)) {

                strb.append(UNDERLINE_CHAR);
                strb.append(Character.toLowerCase(c));
            } else {

                strb.append(c);
            }
        }
        return strb.toString();
    }

    /**
     * 下划线转驼峰
     *
     * @param underlineStr
     * @return
     */
    public static String underline2Camel(String underlineStr) {

        if (Utils.stringIsNullOrEmpty(underlineStr)) {

            return null;
        }

        int len = underlineStr.length();
        StringBuilder strb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {

            char c = underlineStr.charAt(i);
            if (c == UNDERLINE_CHAR && (++i) < len) {

                c = underlineStr.charAt(i);
                strb.append(Character.toUpperCase(c));
            } else {

                strb.append(c);
            }
        }
        return strb.toString();
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("login_name", "111");


    }
}