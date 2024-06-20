package com.example.dentalize.view.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.dentalize.data.repository.PredictRepository


class MainViewModel(private val predictRepository: PredictRepository) : ViewModel() {
    fun getToken() = predictRepository.getToken().asLiveData()
}