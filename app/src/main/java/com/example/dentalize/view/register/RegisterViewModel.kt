package com.example.dentalize.view.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentalize.data.repository.PredictRepository
import com.example.dentalize.data.response.RegisterResponse
import com.example.dentalize.data.result.ResultState
import kotlinx.coroutines.launch
import retrofit2.HttpException


class RegisterViewModel(private val predictRepository: PredictRepository) : ViewModel() {
    private val _responseResult = MutableLiveData<ResultState<RegisterResponse>>()
    val responseResult : LiveData<ResultState<RegisterResponse>> = _responseResult

    fun  submitRegister(username: String, email: String, password: String){
        viewModelScope.launch {
            try {
                _responseResult.value
                val response = RegisterResponse(username,email,password)
                if (response.username!!.isNotEmpty()){
                    _responseResult.value = ResultState.Success(response)
                }
            }catch (e: HttpException){
                val errorBody = e.response()?.errorBody()?.string()
                _responseResult.value
            }
        }
    }
}