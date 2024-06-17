package com.example.dentalize.view.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.dentalize.data.pref.UserRepository


class MainViewModel(private val repository: UserRepository) : ViewModel() {
    fun getToken() = repository.getToken().asLiveData()
}