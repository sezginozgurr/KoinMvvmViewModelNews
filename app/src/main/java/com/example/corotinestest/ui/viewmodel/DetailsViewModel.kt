package com.example.corotinestest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brkcnszgn.networkresponse.NetworkResponse
import com.example.corotinestest.core.model.ErrorResponse
import com.example.corotinestest.core.model.PhotosResponseModel
import com.example.corotinestest.core.repository.DetailRepository
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: DetailRepository) : ViewModel() {
    private val detailsList = MutableLiveData<PhotosResponseModel>()
    val resultResponse: LiveData<PhotosResponseModel> = detailsList
    private val resultErrorModel = MutableLiveData<ErrorResponse>()
    val responseErrorModel: LiveData<ErrorResponse> = resultErrorModel
    var loading = MutableLiveData<Boolean>()

    fun getDetailsArguments() {
        viewModelScope.launch {
            detailsUser()
        }
    }

    private suspend fun detailsUser() {
        loading.postValue(true)
        when (val body = repository.getDetails()) {
            is NetworkResponse.Success<PhotosResponseModel> -> {
                detailsList.value = body.body
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