package com.suma.cofeeshopfinder.retrofit;

import com.suma.cofeeshopfinder.application.CoffeeShopApp;
import com.suma.cofeeshopfinder.constant.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    //create singleton retrofit class
    public static Retrofit getClient() {
        if (retrofit==null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().retryOnConnectionFailure(true).readTimeout(80, TimeUnit.SECONDS).connectTimeout(80, TimeUnit.SECONDS).addInterceptor(interceptor).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.PLACE_API_BASE_URL).addConverterFactory(CoffeeShopApp.getApiConverterFactory()).client(client).build();
        }
        return retrofit;
    }
}