package com.example.omer.mobileprogramming;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

 class ApiClientInstance {



    private static Retrofit retrofit;


    public static Retrofit getRetrofitInstance() {
        String Base_URL="http://api.openweathermap.org/";
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(Base_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

     public static ApiClient getApiService(){
         ApiClient api = getRetrofitInstance().create(ApiClient.class);
         return api;
     }



}