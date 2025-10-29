package my.id.zaxx.news.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import my.id.zaxx.news.data.api.response.HeadlineResponse
import my.id.zaxx.news.data.api.response.NewsListResponse
import my.id.zaxx.news.repository.NewsRepository
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo : NewsRepository): ViewModel() {

    private val _headlineResponse = MutableLiveData<HeadlineResponse>()
    val headlineResponse: LiveData<HeadlineResponse> = _headlineResponse

    private val _newsListResponse = MutableLiveData<NewsListResponse>()
    val newsListResponse: LiveData<NewsListResponse> = _newsListResponse


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    // TODO: ADD ERROR HANDLING
    fun getHeadlineNews(){

        viewModelScope.launch {
            try {
                val response = repo.getHeadlineNews()
                _headlineResponse.postValue(response)

            }catch (e : HttpException){

            }
        }
    }

    fun getNewsList(){
        _isLoading.postValue(true)
        viewModelScope.launch {
            try {
                val response = repo.getNewsList()
                _newsListResponse.postValue(response)
                _isLoading.postValue(false)
            }catch (e: HttpException){
                _isLoading.postValue(true)

            }
        }
    }


}