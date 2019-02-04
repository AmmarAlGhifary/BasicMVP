package com.blogspot.yourfavoritekaisar.basicmvp.model.network;

import com.blogspot.yourfavoritekaisar.basicmvp.model.login.LoginBody;
import com.blogspot.yourfavoritekaisar.basicmvp.model.login.LoginResponse;
import com.blogspot.yourfavoritekaisar.basicmvp.model.user.SingleUserResponse;
import com.blogspot.yourfavoritekaisar.basicmvp.model.user.UserResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @POST("/api/login")
    Call<LoginResponse> postLogin(@Body LoginBody loginBody);

    // Membuat endpoint get data user
    @GET("/api/users")
    Call<UserResponse> getDataUser(@Query("per_page")int perpage);

    // Membuat endpoint untuk get data single user
    @GET("/api/users/{id}")
    Call<SingleUserResponse> getDataSingleUser(@Path("id")int id);


}
