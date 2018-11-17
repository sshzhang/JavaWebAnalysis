package org.smart4j.chapter1.frameWork.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * 流操作工具类
 */
public class StreamUtil {


    private static final Logger LOGGER = LoggerFactory.getLogger(StreamUtil.class);


    /**
     * 从输入流中读取字符串
     */

    public  static  String getString(InputStream is) {

        StringBuilder builder = new StringBuilder();

        try {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
        } catch (Exception e) {

            LOGGER.error("get string failure", e);
            throw new RuntimeException(e);
        }
        return builder.toString();
    }


}
