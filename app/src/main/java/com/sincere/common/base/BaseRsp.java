package com.sincere.common.base;

import java.io.Serializable;

/**
 * 基础请求体
 */
public class BaseRsp<T> implements Serializable {
    public int code;
    public String message;
    public T content;

    @Override
    public String toString() {
        return "BaseRsp{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", content=" + content +
                '}';
    }
}
