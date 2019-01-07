package com.sincere.common.bean;

import java.io.Serializable;

/**
 * 发送验证码请求体
 */
public class SendSmsReq implements Serializable {
    public String phone;
    public int type;
    public String userName;

    public SendSmsReq(String phone, int type, String userName) {
        this.phone = phone;
        this.type = type;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "SendSmsReq{" +
                "phone='" + phone + '\'' +
                ", type=" + type +
                ", userName='" + userName + '\'' +
                '}';
    }
}
