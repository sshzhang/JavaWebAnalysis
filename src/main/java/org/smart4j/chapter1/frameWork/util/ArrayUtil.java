package org.smart4j.chapter1.frameWork.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 数组工具类
 */
public class ArrayUtil {

    public static boolean isNotEmpty(Object[] array) {
        return ArrayUtils.isNotEmpty(array);
    }


    public static boolean isEmpty(Object[] array) {
        return ArrayUtils.isEmpty(array);
    }

}
