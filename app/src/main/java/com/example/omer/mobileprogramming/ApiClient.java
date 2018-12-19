package com.example.omer.mobileprogramming;

import com.example.omer.mobileprogramming.model.hava.Hava;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiClient {



@GET("data/2.5/weather")
Call<Hava> getHava(@Query("lat") int lat, @Query("lon") int lon, @Query("APPID") String APPID);





}
