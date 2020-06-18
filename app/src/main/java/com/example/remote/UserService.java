package com.example.remote;

import com.example.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {


    @POST("/api/")
    Call<User> addUser(@Body User user);

//    @GET("user/")
//    Call<List<User> getUsers();
//
//    @PUT("update/{id}")
//    Call<User> updateUser(@Path("id") int id, @Body User user);
//
//    @DELETE("delete/{id}")
//    Call<User> deleteUser(@Path("id") int id);
}