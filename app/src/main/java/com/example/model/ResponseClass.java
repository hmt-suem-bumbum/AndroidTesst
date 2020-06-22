package com.example.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResponseClass {
    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("status_code")
    @Expose
    private String status_code;

    public ResponseClass() {
    }

    public ResponseClass(int code, String message, String status_code) {
        this.code = code;
        this.message = message;
        this.status_code = status_code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }
}
