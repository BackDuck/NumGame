package com.example.nurshat.numgame;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Nurshat on 24.09.2015.
 */
public class Settings {
    private int record;
    private String userId;
    private static Settings instance;
    private SharedPreferences setting;
    private SharedPreferences.Editor editor;

    public static Settings getInstance(Context context) {
        if (instance == null) {
            instance = new Settings(context);
        }
        return instance;

    }

    public Settings() {

    }

    public Settings(Context context) {
        setting = context.getSharedPreferences("settings", Context.MODE_PRIVATE);

        if (setting.contains("record")) {
            // Получаем число из настроек
            record = setting.getInt("record", 0);

        } else {
            editor = setting.edit();
            editor.putInt("record", 0);
            editor.apply();
        }

        if (setting.contains("userId")) {
            // Получаем число из настроек
            userId = setting.getString("userId", "");

        } else {
            editor = setting.edit();
            editor.putString("userId", "");
            editor.apply();
        }

    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {

        if (this.record < record) {
            editor = setting.edit();
            editor.putInt("record", record);
            editor.apply();
            this.record = record;
            ServerAdapter sa = new ServerAdapter();
            sa.setRating(userId, String.valueOf(record));
        }

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        editor = setting.edit();
        editor.putString("userId", userId);
        editor.apply();
        this.userId = userId;
    }
}
