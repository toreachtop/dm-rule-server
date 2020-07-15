package com.nbank.dm.rule.server.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Jason帮助类
 *
 * @author MARK
 */
public class JsonUtils {
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * 解析报文
     */
    public static <T> T parse(String content, Class<T> valueType) {
        try {
            return mapper.readValue(content, valueType);
        } catch (IOException e) {
            throw new RuntimeException("解析JSON字符串" + content + "报错：", e);
        }
    }

    /**
     * 组装报文
     */
    public static String assemble(Object value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (IOException e) {
            throw new RuntimeException("组装JSON字符串报错：", e);
        }
    }

}
