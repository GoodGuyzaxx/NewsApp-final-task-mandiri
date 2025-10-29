package my.id.zaxx.news.repository

import my.id.zaxx.news.data.api.ApiService
import my.id.zaxx.news.data.api.response.HeadlineResponse

class NewsRepository(private val apiService: ApiService) {

    suspend fun getHeadlineNews(): HeadlineResponse {
        return apiService.getHeadlineNews()
    }

}