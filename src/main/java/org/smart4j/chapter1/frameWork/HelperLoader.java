package org.smart4j.chapter1.frameWork;

import org.smart4j.chapter1.frameWork.helper.BeanHelper;
import org.smart4j.chapter1.frameWork.helper.ClassHelper;
import org.smart4j.chapter1.frameWork.helper.ControllerHelper;
import org.smart4j.chapter1.frameWork.helper.IocHelper;
import org.smart4j.chapter1.frameWork.util.ClassUtil;

/**
 * 加载相应的Helper类
 */
public final class HelperLoader {

    public  static  void inint() {

        Class<?>[] classList = {ClassHelper.class, BeanHelper.class, IocHelper.class, ControllerHelper.class};

        for (Class<?> cla : classList) {
            ClassUtil.loadClass(cla.getName(), true);
        }
    }

}
