package com.blogspot.yourfavoritekaisar.basicmvp.model.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;
    // Membuat method return getClient
    public static Retrofit getClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
