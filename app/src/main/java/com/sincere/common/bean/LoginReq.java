package com.sincere.common.bean;

import java.io.Serializable;

public class LoginReq implements Serializable {
    public String userName;
    public String password;

    public LoginReq(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginReq{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
