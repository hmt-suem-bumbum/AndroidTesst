package com.example.remote;

public class APIUtils {

    private APIUtils(){
    };

    public static final String API_URL = "http://127.0.0.1:8000/";

    public static UserService getUserService(){
        return RetrofitClient.getClient(API_URL).create(UserService.class);
    }

}

