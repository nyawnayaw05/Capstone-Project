package com.example.dentalize.view.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentalize.data.repository.PredictRepository
import com.example.dentalize.data.response.MessageResponse
import com.example.dentalize.data.result.ResultState
import com.google.gson.Gson
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException


class AddViewModel(private val repository: PredictRepository): ViewModel() {

    private val  _responseResult = MutableLiveData<ResultState<MessageResponse>>()
    val responseResult : MutableLiveData<ResultState<MessageResponse>> = _responseResult
    fun addPredict( multipartBody: MultipartBody.Part, resultBody: RequestBody, createdDateBody: RequestBody,
                    suggestionBody: RequestBody, causeBody: RequestBody, explanationBody: RequestBody,
                    preventionBody: RequestBody){
        viewModelScope.launch {
            try {
                _responseResult.value = ResultState.Loading
                repository.getToken().collect{
                        token ->
                    if (token != null){
                        repository.addPredict(multipartBody, resultBody, createdDateBody,
                            suggestionBody, causeBody, explanationBody, preventionBody)
                        _responseResult.value = ResultState.Success(MessageResponse(error = false, message = "Succes"))
                    }else{
                        _responseResult.value = ResultState.Error("Token not found")
                    }
                }
            }catch (e: HttpException){
                val errorBody = e.response()?.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, MessageResponse::class.java)
                responseResult. value = ResultState.Error(errorResponse.message)
            }
        }
    }


}