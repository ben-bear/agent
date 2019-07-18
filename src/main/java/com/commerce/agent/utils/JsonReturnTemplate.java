package com.commerce.agent.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class JsonReturnTemplate<T> implements Serializable {
    private Integer code;
    private String msg;
    private String stackTrace;
    private T data;
    private long serverTime;

    @SuppressWarnings("unused")
    private List<String> logs;
    private long cost;
    public static final Integer SUCCESS = 1;
    public static final Integer FAILED = 0;
    public static final String SUCCESS_MSG = "成功";

    public JsonReturnTemplate() {
        this(SUCCESS, SUCCESS_MSG);
    }

    public JsonReturnTemplate(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        serverTime = System.currentTimeMillis();
    }

    public static <T> JsonReturnTemplate<T> success() {
        return new JsonReturnTemplate<T>(SUCCESS, SUCCESS_MSG);
    }

    public static <T> JsonReturnTemplate<T> success(T data) {
        JsonReturnTemplate<T> returnTemplate = new JsonReturnTemplate<T>(SUCCESS, SUCCESS_MSG);
        returnTemplate.setData(data);
        return returnTemplate;
    }


    @SuppressWarnings("unchecked")
    public JsonReturnTemplate<T> withData(String key, Object value) {
        Map<String, Object> data = (Map<String, Object>) this.getData();
        if (data == null) {
            data = new HashMap<>();
            setData((T) data);
        }
        data.put(key, value);
        return this;
    }
}