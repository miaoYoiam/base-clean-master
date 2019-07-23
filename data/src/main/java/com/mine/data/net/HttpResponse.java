package com.mine.data.net;

import com.google.gson.annotations.SerializedName;

public class HttpResponse {
    @SerializedName("result")
    public Object result;
    @SerializedName("state")
    private String state;




    public String getStatusMessage() {
        return this.state;
    }
}
