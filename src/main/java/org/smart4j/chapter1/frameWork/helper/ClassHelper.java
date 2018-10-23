package org.smart4j.chapter1.frameWork.helper;

import org.smart4j.chapter1.frameWork.Utils.ClassUtil;
import org.smart4j.chapter1.frameWork.annotation.Controller;
import org.smart4j.chapter1.frameWork.annotation.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * 类操作助手
 */
public class ClassHelper {

    private static final Set<Class<?>> CLASS_SET;
    static {
        String basePackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }

    /**
     * 获取应用包名下的所有类
     */

    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }


    /**
     * 获取应用包名下所有Service类
     */

    public static Set<Class<?>> getServiceClassSet() {

        Set<Class<?>> classSets = new HashSet<>();
        for (Class<?> cls : CLASS_SET) {

            if (cls.isAnnotationPresent(Service.class)) {
                classSets.add(cls);
            }

        }
        return classSets;
    }


    /**
     * 获取应用包名下的所有Controller类
     */

    public static Set<Class<?>> getControllerClassSet() {

        Set<Class<?>> classSet = new HashSet<Class<?>>();

        for (Class<?> cls : classSet) {

            if (cls.isAnnotationPresent(Controller.class)) {
                classSet.add(cls);
            }
        }
        return classSet;
    }


    /**
     * 获取应用下的所有Bean类
     */

    public static Set<Class<?>> getBeanClassSet() {
        Set<Class<?>> classes = new HashSet<>();
        classes.addAll(getControllerClassSet());
        classes.addAll(getServiceClassSet());
        return classes;
    }

}
