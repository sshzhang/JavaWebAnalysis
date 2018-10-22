package org.smart4j.chapter1.frameWork.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 属性文件工作类
 */
public class PropsUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);


    public static Properties loadProps(String fileName) {

        Properties props = null;

        InputStream is = null;

        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if (is == null) {
                throw new FileNotFoundException(fileName + " file is not found");
            }
            props = new Properties();
            props.load(is);
        } catch (IOException ex) {
            LOGGER.error("load properties file failure", ex);
        }finally {

            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    LOGGER.error("close input stream failure", e);
                }
            }
        }
        return props;

    }


    public static String getString(Properties props, String key) {
        return getString(props, key, "");
    }

    public static String getString(Properties props, String key, String dvalue) {

        String value = dvalue;
        if (props.contains(key)) {
            value = props.getProperty(key);
        }
        return value;
    }


    public static int getInt(Properties props, String key) {
        return getInt(props, key, 0);
    }

    public static int getInt(Properties props, String key, int dvalue) {

        int vale = dvalue;
        if (props.contains(key)) {
            vale = CastUtil.castInt(props.getProperty(key));
        }
        return vale;
    }


    public static boolean getBoolean(Properties props, String key) {

        return getBoolean(props, key, false);
    }

    public static boolean getBoolean(Properties props, String key, boolean dvalue) {

        boolean value = dvalue;

        if (props.contains(key)) {
            value = CastUtil.castBoolean(props.getProperty(key));
        }
        return value;
    }


}
