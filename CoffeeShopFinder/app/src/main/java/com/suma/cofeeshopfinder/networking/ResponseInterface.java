package com.suma.cofeeshopfinder.networking;

import com.suma.cofeeshopfinder.model.NearByApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

    public interface ResponseInterface {

        @GET("api/place/nearbysearch/json?sensor=true&key=AIzaSyAu5WsEe7T3SZMxKzbLdgAtpvLoyiebYj8")
        Call<NearByApiResponse> getNearbyPlaces(@Query("type") String type, @Query("location") String location, @Query("radius") int radius);
    }
