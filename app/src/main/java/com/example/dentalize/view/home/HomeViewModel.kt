package com.example.dentalize.view.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dentalize.data.pref.UserRepository
import com.example.dentalize.data.response.ItemHistoryResponse
import com.example.dentalize.data.response.ItemHistoryResponseItem
import com.example.dentalize.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeViewModel(private val repository: UserRepository): ViewModel() {
    private val _history = MutableLiveData<List<ItemHistoryResponseItem>>()
    val history: MutableLiveData<List<ItemHistoryResponseItem>> = _history

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val  TAG = "HomeViewModel"
    }

    private suspend fun findUser(result: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getHistory(result)
        client.enqueue(object : Callback<ItemHistoryResponse> {
            override fun onResponse(
                call: Call<ItemHistoryResponse>,
                response: Response<ItemHistoryResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _history.value = responseBody.itemHistoryResponse
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ItemHistoryResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })

    }


}