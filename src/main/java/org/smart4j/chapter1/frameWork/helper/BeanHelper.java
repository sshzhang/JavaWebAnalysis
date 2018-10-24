package org.smart4j.chapter1.frameWork.helper;

import org.smart4j.chapter1.frameWork.Utils.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Bean 助手类
 */
public class BeanHelper {


    /**
     * 定义 Bean 映射(用于存放 Bean类与Bean 实例的映射关系)
     */
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();

    static {

        Set<Class<?>> beanClassSet =
                ClassHelper.getBeanClassSet();

        for (Class<?> beanClass : beanClassSet) {
            BEAN_MAP.put(beanClass, ReflectionUtil.newInstance(beanClass));
        }
    }


    /**
     * 获取 Bean映射
     */
    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    /**
     * 获取 Bean实例
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> cls) {

        if (!BEAN_MAP.containsKey(cls)) {
            throw new RuntimeException("can not get bean by class:" + cls);
        }
        return (T) BEAN_MAP.get(cls);
    }


}
