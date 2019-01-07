package com.sincere.common.tools;

import android.content.Context;
import android.content.SharedPreferences;

import com.sincere.common.app.CommonApplication;
import com.google.gson.Gson;

import java.io.Serializable;

/**
 * 数据缓存
 */
public class Preferences {

    protected void saveInt(String key, int value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putInt(key, value);
        editor.commit();
    }

    protected int getInt(String key) {
        return getSharedPreferences().getInt(key, -1);
    }

    protected void saveString(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(key, value);
        editor.commit();
    }

    protected String getString(String key) {
        return getSharedPreferences().getString(key, null);
    }

    protected boolean getBoolean(String key, boolean value) {
        return getSharedPreferences().getBoolean(key, value);
    }

    protected void saveBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    protected void saveLong(String key, long value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putLong(key, value);
        editor.commit();
    }

    protected long getLong(String key, long value) {
        return getSharedPreferences().getLong(key, value);
    }

    protected <T extends Serializable> void saveObj(String key, T t) {
        String json = new Gson().toJson(t);
        saveString(key, json);
    }

    protected <T extends Serializable> T getObj(String key, Class<T> tClass) {
        String json = getString(key);
        T t = null;
        try {
            t = new Gson().fromJson(json, tClass);
        } catch (Exception e) {
            return null;
        }
        return t;
    }

    protected void clean() {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.clear();
        editor.commit();
    }

    static SharedPreferences getSharedPreferences() {
        return CommonApplication.getApplication().getSharedPreferences("zone", Context.MODE_PRIVATE);
    }
}
