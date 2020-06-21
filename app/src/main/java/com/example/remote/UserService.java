package com.example.remote;

import com.example.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {


    @POST("/rest/api/regist/requesters")
    Call<User> addUser(@Body User user);

    @POST("/rest/api/authen/requester/login/{username}/{password}")
    Call<User> login(@Path("username") String username, @Path("password") String password);

}