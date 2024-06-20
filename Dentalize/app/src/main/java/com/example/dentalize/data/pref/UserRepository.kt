package com.example.dentalize.data.pref

import com.example.dentalize.data.retrofit.ApiService
import com.google.gson.JsonObject
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class UserRepository private constructor(
    private val apiService: ApiService,
    private val pref: UserPreference
) {

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

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService,
            userPreference: UserPreference
        ): UserRepository = instance ?: synchronized(this) {
            instance ?: UserRepository(apiService, userPreference)
        }.also { instance = it }
    }
}