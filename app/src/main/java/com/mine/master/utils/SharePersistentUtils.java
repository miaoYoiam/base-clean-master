package com.mine.master.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 轻量持久化
 */
public class SharePersistentUtils {

    public static final String DEFAULT_PREFS_NAME = "douguo";

    private static SharePersistentUtils instance;

    private SharePersistentUtils() {

    }

    public static SharePersistentUtils getInstance() {
        if (instance == null) {
            instance = new SharePersistentUtils();
        }
        return instance;
    }

    public void deletePerference(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(
                DEFAULT_PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(key);
        editor.apply();
    }

    public void savePerference(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(
                DEFAULT_PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void saveInt(Context context, String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(
                DEFAULT_PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public String getPerference(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(
                DEFAULT_PREFS_NAME, 0);
        return settings.getString(key, "");
    }

    public int getInt(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(
                DEFAULT_PREFS_NAME, 0);
        return settings.getInt(key, 0);
    }

    public int getInt(Context context, String key, int defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(
                DEFAULT_PREFS_NAME, 0);
        return settings.getInt(key, defaultValue);
    }

    public void saveBoolean(Context context, String key, boolean value) {
        SharedPreferences settings = context.getSharedPreferences(
                DEFAULT_PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBoolean(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(
                DEFAULT_PREFS_NAME, 0);
        return settings.getBoolean(key, false);
    }
}
