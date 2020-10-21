package com.example.corotinestest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.corotinestest.core.model.ErrorResponse
import com.example.corotinestest.core.model.UserResponseModel
import com.example.corotinestest.core.repository.UserRepository

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val categories = MutableLiveData<UserResponseModel>()
    val resultResponse: LiveData<UserResponseModel> = categories
    private val resultErrorModel = MutableLiveData<ErrorResponse>()
    val responseErrorModel: LiveData<ErrorResponse> = resultErrorModel
    var loading = MutableLiveData<Boolean>()


/*    suspend fun getUser() {

        viewModelScope.launch {
            fetchUser()
        }
    }

    private suspend fun fetchUser() {
        loading.postValue(true)
        when (val body = repository.getUser()) {
            is NetworkResponse.Success<UserResponseModel> -> {
                categories.value = body.body
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
    }*/
}