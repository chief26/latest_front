package com.example.shaunmesias.assignment_6_2.conf.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by Shaun Mesias on 2016/05/07.
 */
public class App extends Application{
    private static Context context;
    private static App singleton;

    public static final String TAG = App.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
        singleton = this;
    }

    public static Context getAppContext(){
        return App.context;
    }

    public static synchronized App getInstance() {
        return singleton;
    }
}
