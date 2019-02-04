package com.blogspot.yourfavoritekaisar.basicmvp.model.user;

import com.google.gson.annotations.SerializedName;

public class SingleUserResponse {
    @SerializedName("data")
    private UserData userdata;

    public UserData getData() {
        return userdata;
    }

    public void setData(UserData data) {
        this.userdata = data;
    }
}

