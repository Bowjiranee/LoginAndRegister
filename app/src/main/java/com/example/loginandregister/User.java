package com.example.loginandregister;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("response")
    private String Response;

    @SerializedName("user_email")
    private String Email;

    public String getResponse() {
        return Response;
    }

    public String getEmail() {
        return Email;
    }

}
