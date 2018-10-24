package org.smart4j.chapter1.frameWork.helper;

import org.smart4j.chapter1.frameWork.Utils.ArrayUtil;
import org.smart4j.chapter1.frameWork.Utils.CollectionUtil;
import org.smart4j.chapter1.frameWork.Utils.ReflectionUtil;
import org.smart4j.chapter1.frameWork.annotation.Inject;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 依赖注入助手类
 */
public final  class IocHelper {

    static {

        Map<Class<?>, Object> beanMap =
                BeanHelper.getBeanMap();
        if (CollectionUtil.isNotEmpty(beanMap)) {
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                Field[] declaredFields =
                        beanClass.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(declaredFields)) {

                    for (Field beanField : declaredFields) {

                        if (beanField.isAnnotationPresent(Inject.class)) {
                            Class<?> beanFieldTypeClass = beanField.getType();
                            Object beanFieldTypeClassValue = beanMap.get(beanFieldTypeClass);
                            if (beanFieldTypeClassValue != null) {
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldTypeClassValue);
                            }
                        }
                    }
                }
            }

        }

    }

}
