package com.suma.cofeeshopfinder.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import retrofit2.converter.gson.GsonConverterFactory;
public class CoffeeShopApp extends Application {

    private static CoffeeShopApp app;
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        context = getApplicationContext();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);

    }
    //create gson gsonconverter factory
    public static GsonConverterFactory getApiConverterFactory() {
        return GsonConverterFactory.create();
    }
    
    public static CoffeeShopApp getApp() {
        return app;
    }

    public static Context getAppContext() {
       return context;
    }
    
}
