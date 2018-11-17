package org.smart4j.chapter1.frameWork.bean;

import org.smart4j.chapter1.frameWork.util.CastUtil;

import java.util.Map;

/**
 * 请求参数对象
 *
 */
public class Param {

    private Map<String, Object> paramMap;


    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    /**
     * 根据参数名获取 long 型参数
     * @param name
     * @return
     */
    public long getLong(String name) {
        return CastUtil.castLong(paramMap.get(name));
    }

    /**
     * 获取所有字段信息
     * @return
     */
    public Map<String, Object> getMap() {
        return paramMap;
    }


}
