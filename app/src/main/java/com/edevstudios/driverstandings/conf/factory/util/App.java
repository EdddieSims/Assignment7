package com.edevstudios.driverstandings.conf.factory.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by Edmund.Simons on 2016/05/07.
 */
public class App extends Application
{
    public static Context context;

    private static App singleton;

    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
        singleton = this;
    }

    public static Context getAppContext() {
        return App.context;
    }

    public static final String TAG = App.class
            .getSimpleName();


    public static synchronized App getInstance() {
        return singleton;
    }
}
