package com.artamonov.lessons.network;

import com.artamonov.lessons.models.User;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceHolderApi {

    @GET("/api/user")
    public Call<User> getUser();

}
