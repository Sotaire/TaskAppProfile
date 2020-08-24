package com.example.taskapp;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    private SharedPreferences sharedPreferences;
    public Prefs(Context context) {
        sharedPreferences = context.getSharedPreferences("settings",Context.MODE_PRIVATE);
    }
    public void setShown(){
        sharedPreferences.edit().putBoolean("isShown",true).apply();
    }

    public boolean isShown(){
        return sharedPreferences.getBoolean("isShown",false);
    }

    public void setImage(String uri){
        sharedPreferences.edit().putString("image",uri).apply();
    }

    public String getImage(){
        return sharedPreferences.getString("image",null);
    }
}
