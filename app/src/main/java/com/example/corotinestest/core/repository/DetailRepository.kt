package com.example.corotinestest.core.repository

import com.example.corotinestest.core.service.Api

class DetailRepository(private val service: Api) {

    suspend fun getDetails() = service.getUserPhotos()
}