package com.suma.cofeeshopfinder.retrofit;

import com.suma.cofeeshopfinder.networking.ResponseInterface;

/**
 * Created by suma on 26/09/17.
 */

public class ApiUtils {

    // get Response Interface for api call
    public static ResponseInterface getApiService() {
        return RetrofitClient.getClient().create(ResponseInterface.class);
    }
}
