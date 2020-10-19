package com.example.corotinestest.core.repository

import com.example.corotinestest.core.service.Api

class LastScreenRepository(private val service: Api) {
    suspend fun getComments() = service.getUserComment()
}