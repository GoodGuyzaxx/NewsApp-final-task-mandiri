package my.id.zaxx.news.data.api

import my.id.zaxx.news.data.api.response.HeadlineResponse
import retrofit2.http.GET

interface ApiService {

    @GET("top-headlines?country=us&apiKey=a0391d3c994a42ac9be4004b2eb6e89c")
    suspend fun getHeadlineNews(): HeadlineResponse
}