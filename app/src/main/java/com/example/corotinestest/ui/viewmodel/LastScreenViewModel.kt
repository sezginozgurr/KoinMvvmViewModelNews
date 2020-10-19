package com.example.corotinestest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brkcnszgn.networkresponse.NetworkResponse
import com.example.corotinestest.core.model.CommentResponseModel
import com.example.corotinestest.core.model.ErrorResponse
import com.example.corotinestest.core.repository.LastScreenRepository
import kotlinx.coroutines.launch

class LastScreenViewModel(val repository: LastScreenRepository) : ViewModel() {

    private val comments = MutableLiveData<CommentResponseModel>()
    val resultResponse: LiveData<CommentResponseModel> = comments
    private val resultErrorModel = MutableLiveData<ErrorResponse>()
    val responseErrorModel: LiveData<ErrorResponse> = resultErrorModel
    var loading = MutableLiveData<Boolean>()

    suspend fun getComments() {
        viewModelScope.launch {
            showComment()
        }
    }

    private suspend fun showComment() {
        when (val body = repository.getComments()) {
            is NetworkResponse.Success<CommentResponseModel> -> {
                comments.value = body.body
                loading.value = false
            }
            is NetworkResponse.ApiError<ErrorResponse> -> {
                loading.value = false

                resultErrorModel.value = body.body

            }
            is NetworkResponse.NetworkError -> {
                loading.postValue(false)
            }
            is NetworkResponse.UnknownError -> {
                loading.postValue(false)

            }
        }
    }
}