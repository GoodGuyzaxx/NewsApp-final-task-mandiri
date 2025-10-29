package my.id.zaxx.news.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import my.id.zaxx.news.data.api.response.HeadlineResponse
import my.id.zaxx.news.repository.NewsRepository
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo : NewsRepository): ViewModel() {

    private val _headlineResponse = MutableLiveData<HeadlineResponse>()
    val headlineResponse: LiveData<HeadlineResponse> = _headlineResponse


    fun getHeadlineNews(){
        viewModelScope.launch {
            try {
                val response = repo.getHeadlineNews()
                _headlineResponse.postValue(response)
            }catch (e : HttpException){

            }
        }
    }


}