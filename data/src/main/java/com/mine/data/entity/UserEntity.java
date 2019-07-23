package com.mine.data.entity;

import com.google.gson.annotations.SerializedName;

public class UserEntity {
    @SerializedName("id")
    private int userId;

    public UserEntity() {
        //empty
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


}
