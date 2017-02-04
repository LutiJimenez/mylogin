package com.luti.mylogin.App;

import android.app.Application;
import android.os.SystemClock;

/**
 * Created by Luti on 4/2/17.
 */
public class MyApp extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        SystemClock.sleep(3000);
    }
}
