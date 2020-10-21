package com.example.corotinestest.core.repository

import com.example.corotinestest.core.service.Api

class HomePageRepository(val service: Api) {

    suspend fun getNewsHome() = service.getHomeNews()
}