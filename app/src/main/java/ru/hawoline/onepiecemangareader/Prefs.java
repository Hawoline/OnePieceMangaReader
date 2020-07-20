package ru.hawoline.onepiecemangareader;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.ArrayList;

public class Prefs {
    private final static String PREF_FILE = "MangaSettings";

    public static final String PREF_MANGA_CHAPTERS = "PREF_MANGA_CHAPTERS";

    private SharedPreferences sharedPreferences;
    private static Prefs instance;

    public static Prefs getInstance() {
        return instance;
    }

    public static void init(Context context) {
        if (instance == null) {
            instance = new Prefs(context);
        }
    }

    private Prefs(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        }
    }

    public void removeKey(String key) {
        sharedPreferences.edit().remove(key).apply();
    }

    public void setString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public void setBoolean(String key, Boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public void setInt(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    public void setLong(String key, long value) {
        sharedPreferences.edit().putLong(key, value).apply();
    }

    public String getString(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }

    public long getLong(String key, long defValue) {
        return sharedPreferences.getLong(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }


}
