package com.example.madcamp_week3;

import android.content.SharedPreferences;

public class User {
    private String userid;
    private String password;

    public User(String userid, String password) {
        this.userid = userid;
        this.password = password;

    }

    public String getUserid() {
        return userid;
    }

    public String getPassword() {
        return password;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
