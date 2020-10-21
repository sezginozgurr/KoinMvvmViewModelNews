package com.example.corotinestest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brkcnszgn.networkresponse.NetworkResponse
import com.example.corotinestest.core.model.ErrorResponse
import com.example.corotinestest.core.model.trnews.TurkeyNewsModel
import com.example.corotinestest.core.repository.HomePageRepository
import kotlinx.coroutines.launch

class HomePageViewModel(private val repository: HomePageRepository) : ViewModel() {

    private val news = MutableLiveData<TurkeyNewsModel>()
    val resultResponse: LiveData<TurkeyNewsModel> = news
    private val resultErrorModel = MutableLiveData<ErrorResponse>()
    val responseErrorModel: LiveData<ErrorResponse> = resultErrorModel
    var loading = MutableLiveData<Boolean>()


    fun getHomeNews() {
        viewModelScope.launch {
            homeNews()
        }
    }

    private suspend fun homeNews() {
        loading.postValue(true)
        when (val body = repository.getNewsHome()) {
            is NetworkResponse.Success<TurkeyNewsModel> -> {
                news.value = body.body
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