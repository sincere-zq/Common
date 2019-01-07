package com.sincere.common.bean;

import java.io.Serializable;

public class UserInfo implements Serializable {
    public String name;//姓名
    public int sex;//性别
    public String phone;//手机号码
    public String idCard;//证件号码
    public String idCardType;//证件类型
    public String birthday;//生日
    public String memCode;//会员卡号
    public String memGradeId;//等级编号
    public String loginPwd;//登录密码
    public String payPwd;//支付密码
    public String cumulativeRecharge;//累积充值
    public String rechargeBalance;//充值金额
    public String giftBalance;//赠送金额
    public String cumulativeConsumption;//累积消费
    public String cumulativeIntegral;//累积积分
    public String usedIntegral;//已用积分
    public String availableIntegral;//可用积分
    public String cardTime;//发卡时间
    public String createId;//操作人编号
    public String salespersonId;//销售员编号
    public String id;//主键（新增是不需要添加）
    public String modifyTime;//修改时间
    public String createTime;//创建时间

    @Override
    public String toString() {
        return "UserBean{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", phone='" + phone + '\'' +
                ", idCard='" + idCard + '\'' +
                ", idCardType='" + idCardType + '\'' +
                ", birthday='" + birthday + '\'' +
                ", memCode='" + memCode + '\'' +
                ", memGradeId='" + memGradeId + '\'' +
                ", loginPwd='" + loginPwd + '\'' +
                ", payPwd='" + payPwd + '\'' +
                ", cumulativeRecharge='" + cumulativeRecharge + '\'' +
                ", rechargeBalance='" + rechargeBalance + '\'' +
                ", giftBalance='" + giftBalance + '\'' +
                ", cumulativeConsumption='" + cumulativeConsumption + '\'' +
                ", cumulativeIntegral='" + cumulativeIntegral + '\'' +
                ", usedIntegral='" + usedIntegral + '\'' +
                ", availableIntegral='" + availableIntegral + '\'' +
                ", cardTime='" + cardTime + '\'' +
                ", createId='" + createId + '\'' +
                ", salespersonId='" + salespersonId + '\'' +
                ", id='" + id + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
