package com.example.madcamp_week3;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class StringArrayPref {

    public StringArrayPref() {}

    public void setStringArrayPref(Context context, String key, ArrayList<String> values) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < values.size(); i++) {
            jsonArray.put(values.get(i));
        }

        if (!values.isEmpty()) {
            editor.putString(key, jsonArray.toString());
        } else {
            editor.putString(key, null);
        }

        editor.apply();

    }

    public ArrayList<String> getStringArrayPref(Context context, String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonArrayString = sharedPreferences.getString(key, null);
        ArrayList<String> values = new ArrayList<String>();

        if (jsonArrayString != null) {
            try {
                JSONArray jsonArray = new JSONArray(jsonArrayString);
                for (int i = 0; i< jsonArray.length(); i++) {
                    String value = jsonArray.optString(i);
                    values.add(value);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return values;
    }
}
