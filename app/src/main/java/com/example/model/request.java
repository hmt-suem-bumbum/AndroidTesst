package com.example.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class request {
    @SerializedName("device")
    @Expose
    String device;
    @SerializedName("request_date")
    @Expose
    String request_date;

    public request(String device, String request_date, String name) {
        this.device = device;
        this.request_date = request_date;
        this.name = name;
    }

    @SerializedName("name")
    @Expose
    String name;

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getRequest_date() {
        return request_date;
    }

    public void setRequest_date(String request_date) {
        this.request_date = request_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
