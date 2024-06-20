package com.example.dentalize.view.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentalize.data.pref.UserRepository
import com.example.dentalize.data.response.LoginResponse
import com.example.dentalize.data.result.ResultState
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(private val repository: UserRepository) : ViewModel() {
    val loginResponse = MutableLiveData<ResultState<LoginResponse>>()

    fun login (email : String, password : String) {
        viewModelScope.launch {
            try {
                val response = repository.login(email, password)
                if (response.username!!.isNotEmpty()) {
                    Log.d("response", "${response.username}")
                    loginResponse.value = ResultState.Success(LoginResponse(username = response.username))
                    repository.saveToken(response.token!!)
                }
            }catch (e: HttpException){
                val errorBody = e.response()?.errorBody()?.string()
                loginResponse.value = ResultState.Error(errorBody?:e.message())
            }
        }
    }
}