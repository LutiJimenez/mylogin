package com.luti.mylogin.Utils;

import android.content.SharedPreferences;

/**
 * Created by Luti on 4/2/17.
 */
public class Util {

    public static String getUserMailPrefs(SharedPreferences preferences){
        return preferences.getString("email", "" );
    }

    public static String getPassPrefs(SharedPreferences preferences){
        return preferences.getString("pass", "" );
    }

    public static void removeSharedPreferences(SharedPreferences preferences){
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("email");
        editor.remove("pass");
        editor.apply();
    }


}
