package com.mine.domain.repository.params;

import com.google.gson.annotations.SerializedName;

public class UserLoginParams {
    public int id = 100;

    @SerializedName("user_name")
    public String userName;
    @SerializedName("user_password")
    public String userPassword;
}
