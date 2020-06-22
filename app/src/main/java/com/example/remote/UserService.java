package com.example.remote;

import com.example.model.ResponseClass;
import com.example.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {


    @POST("/rest/api/regist/requesters")
    Call<ResponseClass> addUser(@Body User user);

    @POST("/rest/api/authen/requester/login")
    Call<ResponseClass> login(@Body User user);

}