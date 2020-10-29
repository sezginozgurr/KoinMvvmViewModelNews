package com.example.corotinestest.core.repository

import com.example.corotinestest.core.service.Api
import com.example.corotinestest.ui.util.Constants

class HomePageRepository(val service: Api) {

    suspend fun getNewsHome() = service.getHomeNews(Constants.apiKey)
}