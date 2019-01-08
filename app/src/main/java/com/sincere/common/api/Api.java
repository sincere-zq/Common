package com.sincere.common.api;

import com.sincere.common.base.BaseRsp;
import com.sincere.common.bean.LoginReq;
import com.sincere.common.bean.LoginRsp;
import com.sincere.common.bean.RegistReq;
import com.sincere.common.bean.SendSmsReq;
import com.sincere.common.bean.UserInfo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by 曾强 on 2018/6/5.
 * api
 */

public interface Api {
    String SEND_SMS = "api/MemberUser/SendSMS";//发送验证码
    String REGISTER = "api/MemberUser/Register";//注册
    String LOGIN = "api/MemberUser/Login";//登录
    String GETBANNERIMAGES = "api/MemberBook/GetBannerImages";//获取首页banner

    @POST(SEND_SMS)
    Observable<BaseRsp<String>> sendSms(@Body SendSmsReq req);

    @POST(REGISTER)
    Observable<BaseRsp<UserInfo>> regist(@Body RegistReq req);

    @POST(LOGIN)
    Observable<BaseRsp<LoginRsp>> login(@Body LoginReq req);

    @GET(GETBANNERIMAGES)
    Observable<BaseRsp<List<String>>> banner();
}
