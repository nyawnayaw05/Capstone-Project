package com.example.dentalize.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dentalize.data.repository.PredictRepository
import com.example.dentalize.data.response.PredictResponse
import com.example.dentalize.data.result.ResultState


class HomeViewModel(private val repository: PredictRepository): ViewModel() {
    val predictResponse = MutableLiveData<ResultState<List<PredictResponse>>>()
}