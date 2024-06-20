package com.example.dentalize.data.repository

import com.example.dentalize.data.pref.UserPreference
import com.example.dentalize.data.retrofit.ApiService
import com.google.gson.JsonObject
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.http.Part

class PredictRepository private constructor(
    private val apiService: ApiService,
    private val pref: UserPreference,
//    private val storyDatabase: StoryDatabase
)  {

    suspend fun saveToken(token: String) = pref.saveToken(token)

    fun getToken() = pref.getToken()

    suspend fun register(username: String, email: String, password: String) =
        apiService.register(username, email, password)

    suspend fun login(username: String, password: String) = apiService.login(
        JsonObject().apply {
            addProperty("username", username)
            addProperty("password", password)
        }.toString()
            .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
    )

    suspend fun addPredict(
        multipartBody: MultipartBody.Part,
        resultBody: RequestBody,
        createdDateBody: RequestBody,
        suggestionBody: RequestBody,
        causeBody: RequestBody,
        explanationBody: RequestBody,
        preventionBody: RequestBody
    ) = apiService.uploadImage(multipartBody, resultBody, createdDateBody,
        suggestionBody, causeBody, explanationBody, preventionBody)

    companion object {
        @Volatile
        private var instance: PredictRepository? = null
        fun getInstance(
            apiService: ApiService,
            userPreference: UserPreference
        ): PredictRepository = instance ?: synchronized(this) {
            instance ?: PredictRepository(apiService, userPreference)
        }.also { instance = it }
    }
}
