package com.example.dentalize.data.retrofit

import com.example.dentalize.data.response.LoginResponse
import com.example.dentalize.data.response.RegisterResponse
import com.google.mlkit.nl.entityextraction.Entity.Type
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
         @Field("username") username: String,
         @Field("email") email: String,
         @Field("password") password: String
    ): RegisterResponse

    @Headers("Content-Type: application/json")
    @POST("login")
    suspend fun login(
        @Body body: RequestBody
//        @Field("username") username: String,
//        @Field("password") password: String
    ): LoginResponse
}