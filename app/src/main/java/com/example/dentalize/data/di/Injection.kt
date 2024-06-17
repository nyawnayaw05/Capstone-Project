package com.example.dentalize.data.di

import android.content.Context
import com.example.dentalize.data.pref.UserPreference
import com.example.dentalize.data.pref.UserRepository
import com.example.dentalize.data.pref.dataStore
import com.example.dentalize.data.retrofit.ApiConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val token = runBlocking { pref.getToken().first() }
        val apiService = ApiConfig.getApiService(token)
        return UserRepository.getInstance(apiService, pref)
    }
}