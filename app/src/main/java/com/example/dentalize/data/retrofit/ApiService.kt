package com.example.dentalize.data.retrofit

import com.example.dentalize.data.response.ItemHistoryResponse
import com.example.dentalize.data.response.LoginResponse
import com.example.dentalize.data.response.PredictResponse
import com.example.dentalize.data.response.RegisterResponse
import com.example.dentalize.data.response.ResultResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.*

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

    @Multipart
    @POST("upload")
    suspend fun uploadPredict(
        @Part image: MultipartBody.Part,
        @Part("result") result: RequestBody,
        @Part("createdDate") createdDate: RequestBody,
        @Part("suggestion") suggestion: RequestBody,
        @Part("cause") cause: RequestBody,
        @Part("explanation") explanation: RequestBody,
        @Part("prevention") prevention: RequestBody
    ):PredictResponse

    @GET("history")
     fun getHistory(
        @Query("q") result: String
    ):Call<ItemHistoryResponse>

    @GET("result")
    fun getResult(
        @Path("result") result: String
    ): Call <ResultResponse>


}