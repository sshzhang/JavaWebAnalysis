package org.smart4j.chapter1.frameWork.helper;
import org.smart4j.chapter1.frameWork.util.ArrayUtil;
import org.smart4j.chapter1.frameWork.util.CollectionUtil;
import org.smart4j.chapter1.frameWork.util.ReflectionUtil;
import org.smart4j.chapter1.frameWork.annotation.Inject;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * 依赖注入助手类
 */
public final class IocHelper {
    static {
        System.out.println("IocHelper.class Init!");
        //获取Class对象和 对应实例化对象的键值对 (包括注解Controller和Service标注的类)
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
                        //查找每一个需要依赖注入的字段
                        if (beanField.isAnnotationPresent(Inject.class)) {
                            Class<?> beanFieldTypeClass = beanField.getType();
                            //获取对用注解类型的  实例对象
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
