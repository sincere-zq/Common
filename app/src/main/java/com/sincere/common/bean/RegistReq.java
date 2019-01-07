package com.sincere.common.bean;

import java.io.Serializable;

public class RegistReq implements Serializable {
    public String phone;
    public String code;
    public String password;

    public RegistReq(String phone, String code, String password) {
        this.phone = phone;
        this.code = code;
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegistReq{" +
                "phone='" + phone + '\'' +
                ", code=" + code +
                ", password='" + password + '\'' +
                '}';
    }
}
