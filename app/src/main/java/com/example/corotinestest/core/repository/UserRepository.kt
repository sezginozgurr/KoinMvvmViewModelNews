package com.example.corotinestest.core.repository

import com.example.corotinestest.core.service.Api

class UserRepository(private val api: Api) {

    suspend fun getUser() = api.getUser()

}