package my.id.zaxx.news.repository

import my.id.zaxx.news.data.api.ApiService
import my.id.zaxx.news.data.api.response.HeadlineResponse
import my.id.zaxx.news.data.api.response.NewsListResponse

class NewsRepository(private val apiService: ApiService) {

    suspend fun getHeadlineNews(): HeadlineResponse {
        return apiService.getHeadlineNews()
    }

    suspend fun getNewsList(): NewsListResponse {
        return apiService.getNewsList()
    }

}