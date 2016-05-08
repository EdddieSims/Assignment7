package com.edevstudios.driverstandings.conf.factory.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by Edmund.Simons on 2016/05/07.
 */
public class App extends Application
{
    private static Context context;

    public static Context getAppContext() {
        return App.context;
    }
}
