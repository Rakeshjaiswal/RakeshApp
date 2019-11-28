package com.rakesh.in.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServicesContact {


    public static String Basew_URl = "https://api.androidhive.info/json/";
    private static ApiClient api_;


    public static ApiClient getInstance() {
        if (api_ == null) {
            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Basew_URl)
                    .client(okHttpClient)
                    .build();
            ApiClient api_ = retrofit.create(ApiClient.class);
            return api_;

        } else
            return api_;
    }
}
