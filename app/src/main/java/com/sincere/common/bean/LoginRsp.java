package com.sincere.common.bean;

import java.io.Serializable;

public class LoginRsp implements Serializable {
    public String token;
    public UserInfo member;

    @Override
    public String toString() {
        return "LoginRsp{" +
                "token='" + token + '\'' +
                ", member=" + member +
                '}';
    }
}
