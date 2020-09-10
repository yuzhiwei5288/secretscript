package com.ace.secretscript.common.util;

import java.lang.reflect.Field;

/**
 * @author ace
 * @version V1.0
 * @title ReflectHelper.java
 * @package com.xiye.common.util
 * @description 反射工具
 * @date 2020-03-27
 */
public final class ReflectHelper {

    private ReflectHelper() {
        throw new IllegalStateException("Utility class");
    }
    /**
     * @Author ace
     * @Date 2020-03-27 16:31:58
     * @Description 获取obj对象fieldName的Field
     * @Param [obj, fieldName]
     * @Return java.lang.reflect.Field
     */
    public static Field getFieldByFieldName(Object obj, String fieldName) {
        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                LoggerUtil.info("");
            }
        }
        return null;
    }

    /**
     * @Author ace
     * @Date 2020-03-27 16:32:09
     * @Description 获取obj对象fieldName的属性值
     * @Param [obj, fieldName]
     * @Return java.lang.Object
     */
    public static Object getValueByFieldName(Object obj, String fieldName) throws IllegalAccessException {
        Field field = getFieldByFieldName(obj, fieldName);
        Object value = null;
        if (field != null) {
            if (field.isAccessible()) {
                value = field.get(obj);
            } else {
                field.setAccessible(true);
                value = field.get(obj);
                field.setAccessible(false);
            }
        }
        return value;
    }

    /**
     * @Author ace
     * @Date 2020-03-27 16:32:18
     * @Description 设置obj对象fieldName的属性值
     * @Param [obj, fieldName, value]
     * @Return void
     */
    public static void setValueByFieldName(Object obj, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        if (field.isAccessible()) {
            field.set(obj, value);
        } else {
            field.setAccessible(true);
            field.set(obj, value);
            field.setAccessible(false);
        }
    }
}
