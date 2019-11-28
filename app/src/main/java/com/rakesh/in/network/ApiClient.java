package com.rakesh.in.network;

import com.rakesh.in.model.Contact;
import com.rakesh.in.model.LoginRequest;
import com.rakesh.in.model.Product;
import com.rakesh.in.model.RegisterRequest;
import com.rakesh.in.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiClient {
    @POST("login")
    Call<User> login(@Body LoginRequest loginRequest);

    @POST("register")
    Call<User> register(@Body RegisterRequest registerRequest);


    @GET("contacts.json")
    Call<List<Contact>> getContactList();


    @GET("/Oclemy/SampleJSON/338d9585/spacecrafts.json")
    Call<List<Product>> getProductList();

}