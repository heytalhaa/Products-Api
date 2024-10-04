package com.example.eshopping.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class SharedPrefs {

    private static final String PREFERENCE_NAME = "MyPreferences";

    private static SharedPreferences getPrefs(Context context){
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    //SharedPreference method for boolean
    public static void saveBoolean(Context context,String key, boolean value){
        SharedPreferences prefs = getPrefs(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }
    public static boolean getBoolean(Context context, String key, boolean value){
        SharedPreferences prefs = getPrefs(context);
        return prefs.getBoolean(key, value);
    }

    //SharedPreference Method for String
    public static void saveString(Context context, String key, String value){
        SharedPreferences prefs = getPrefs(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }
    public static String getString(Context context, String key, String dValue){
        SharedPreferences prefs = getPrefs(context);
        return prefs.getString(key, dValue);
    }

    //SharedPreference method for Objects
    public static <T> void saveObjects(Context context,String key, T value ){
        SharedPreferences prefs = getPrefs(context);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(value);
        editor.putString(key, json);
        editor.apply();
    }
    public static <T> T getObject(Context context, String key, Class<T> clazz){
        SharedPreferences prefs = getPrefs(context);
        String value = prefs.getString(key, null);
        Gson gson = new Gson();
        if (value != null) {
            return gson.fromJson(value, clazz);
        }else {
            return null;
        }
    }
}
