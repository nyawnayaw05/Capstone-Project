package com.example.dentalize.view.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentalize.data.pref.UserRepository
import com.example.dentalize.data.response.RegisterResponse
import com.example.dentalize.data.result.ResultState
import kotlinx.coroutines.launch
import retrofit2.HttpException


class RegisterViewModel(private val repository: UserRepository) : ViewModel() {
    private val _responseResult = MutableLiveData<ResultState<RegisterResponse>>()
    val responseResult = _responseResult

    fun  submitRegister(username: String, email: String, password: String){
        viewModelScope.launch {
            try {
                _responseResult.value
                val response = repository.register(username,email,password)
                if (response.message!!.isNotEmpty()){
                    _responseResult.value = ResultState.Success(response)
                }
            }catch (e: HttpException){
                val errorBody = e.response()?.errorBody()?.string()
                _responseResult.value
            }
        }
    }
}