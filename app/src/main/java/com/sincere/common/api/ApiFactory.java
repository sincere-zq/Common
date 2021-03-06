package com.sincere.common.api;

import com.sincere.common.base.BaseRetrofit;

/**
 * Created by 曾强 on 2018/6/5.
 * api获取
 */

public class ApiFactory {
    private String baseUrl = "http://222.85.133.48:40001/";
    private volatile static Api api;

    public static Api getApi() {
        if (api == null) {
            synchronized (ApiFactory.class) {
                if (api == null) {
                    new ApiFactory();
                }
            }
        }
        return api;
    }

    private ApiFactory() {
        BaseRetrofit retrofit = new BaseRetrofit();
        api = retrofit.getRetrofit(baseUrl).create(Api.class);
    }
}
