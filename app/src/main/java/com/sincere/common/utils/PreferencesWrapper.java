package com.sincere.common.utils;


import com.sincere.common.bean.UserInfo;
import com.sincere.common.tools.Preferences;

public class PreferencesWrapper extends Preferences {
    private static final String USER_INFO = "user_info";
    private static final String USER_TOKEN = "user_token";

    private static PreferencesWrapper preferencesWrapper;

    private PreferencesWrapper() {
    }

    public static PreferencesWrapper getPreferencesWrapper() {
        if (preferencesWrapper == null) {
            synchronized (PreferencesWrapper.class) {
                if (preferencesWrapper == null) {
                    preferencesWrapper = new PreferencesWrapper();
                }
            }
        }
        return preferencesWrapper;
    }

    /**
     * 保存用户信息
     *
     * @param userInfo
     */
    public synchronized void saveUserInfo(UserInfo userInfo) {
        saveObj(USER_INFO, userInfo);
    }

    /**
     * 获取用户信息
     */
    public synchronized UserInfo getUserInfo() {
        return getObj(USER_INFO, UserInfo.class);
    }

    /**
     * 保存用户token
     *
     * @param token
     */
    public synchronized void saveUserToken(String token) {
        saveString(USER_TOKEN, token);
    }

    /**
     * 获取用户信息
     */
    public synchronized String getUserToken() {
        return getString(USER_TOKEN);
    }

}
