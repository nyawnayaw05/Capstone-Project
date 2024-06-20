package com.example.dentalize.view.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentalize.data.repository.PredictRepository
import com.example.dentalize.data.response.LoginResponse
import com.example.dentalize.data.result.ResultState
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(private val predictRepository: PredictRepository) : ViewModel() {
    private val _responseResult = MutableLiveData<ResultState<LoginResponse>>()
    val responseResult : LiveData<ResultState<LoginResponse>> get() = _responseResult

    fun login (email: String, username : String) {
        viewModelScope.launch {
            try {
                val response = LoginResponse("1", email, username, "token")
                _responseResult.postValue(ResultState.Success(response))
            }catch (e: HttpException){
                val errorBody = e.response()?.errorBody()?.string()
                _responseResult.value = ResultState.Error(errorBody?:e.message())
            }
        }
    }
}