package com.example.loginandregister;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface
{
    @GET("register.php")
    Call<User> performRegistration(@Query("user_name") String name, @Query("user_surname") String surname,
                                   @Query("user_email") String email, @Query("user_phonenumber") String phonenumber,
                                   @Query("user_password") String password);
//    Call<User> performRegistration(@Query("user_email") String email, @Query("user_password") String password);

    @GET("login.php")
    Call<User> performUserLogin(@Query("user_email") String email, @Query("user_password") String password);
}
