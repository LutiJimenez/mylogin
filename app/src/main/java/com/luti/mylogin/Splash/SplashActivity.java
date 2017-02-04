package com.luti.mylogin.Splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.luti.mylogin.Activities.LoginActivity;
import com.luti.mylogin.Activities.MainActivity;
import com.luti.mylogin.Utils.Util;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        Intent intentLogin =  new Intent(this, LoginActivity.class);
        Intent intentMain =  new Intent(this, MainActivity.class);

        if(!TextUtils.isEmpty(Util.getUserMailPrefs(prefs)) && !TextUtils.isEmpty(Util.getPassPrefs(prefs))){
            startActivity(intentMain);
        }
        else{
            startActivity(intentLogin);
        }
        //matamos la instancia del activity
        finish();
    }




}
