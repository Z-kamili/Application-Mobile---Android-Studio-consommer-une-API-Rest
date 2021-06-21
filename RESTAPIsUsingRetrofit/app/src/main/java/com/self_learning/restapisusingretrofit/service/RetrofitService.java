package com.self_learning.restapisusingretrofit.service;

import com.self_learning.restapisusingretrofit.model.LoginRequest;
import com.self_learning.restapisusingretrofit.model.User;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface RetrofitService {

    @GET(value = "list")
    Call<List<User>> _GetUsers(@Header("Authorization") String token);

    @POST(value = "register")
    Call<User> _REGISTERMethod(@Body User user);

    @FormUrlEncoded
    @POST(value = "register")
    Call<User> _REGISTERMethod(
            @Field(value = "firstname") String firstname,
            @Field(value = "lastname") String lastname,
            @Field(value = "phone") String phone,
            @Field(value = "email") String email,
            @Field(value = "username") String username
    );

    @FormUrlEncoded
    @POST(value = "register")
    Call<User> _REGISTERMethod(@FieldMap Map<String, String> feilds);

    @POST(value = "login")
    Call<User> _LOGINMethod(@Body LoginRequest user);

    @POST(value = "update")
    @FormUrlEncoded
    Call<User> _PUTRequestMethod(
            @Field(value = "firstname") String firstname,
            @Field(value = "lastname") String lastname,
            @Field(value = "phone") String phone,
            @Field(value = "email") String email,
            @Field(value = "username") String username,
            @Field(value = "isActive") boolean isActive,
            @Field(value = "currentUsername") String currentUsername,
            @Field(value = "role") String role
    );


//    @PATCH(value = "update")
//    @FormUrlEncoded
//    Call<User> _PATCHRequestMethod(
//            @Field(value = "firstname") String firstname,
//            @Field(value = "lastname") String lastname,
//            @Field(value = "phone") String phone,
//            @Field(value = "email") String email,
//            @Field(value = "username") String username,
//            @Field(value = "isActive") boolean isActive,
//            @Field(value = "currentUsername") String currentUsername,
//            @Field(value = "role") String role
//    );



}