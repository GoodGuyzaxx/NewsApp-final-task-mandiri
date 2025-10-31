package my.id.zaxx.news.view.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import my.id.zaxx.news.Adaptor.NewsListAdaptor
import my.id.zaxx.news.data.api.response.ArticlesNewsItem
import my.id.zaxx.news.databinding.ActivityHomeBinding
import my.id.zaxx.news.util.HelperConvertor


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.swipeRefresh.setOnRefreshListener {
            getHeadlineData()
            getNewsList()
        }

        getHeadlineData()
        getNewsList()

        viewModel.isLoading.observe(this){isLoading ->
            if (isLoading){
                binding.swipeRefresh.isRefreshing = isLoading
                binding.cvHolder.visibility = View.GONE
            } else {
                binding.swipeRefresh.isRefreshing = isLoading
            }
        }

    }

    private fun getHeadlineData() {
        viewModel.getHeadlineNews()
        val randomIndex = (1..10).random()

        viewModel.headlineResponse.observe(this){data ->
            if (data.status == "error"){
                binding.cvHolder.visibility = View.GONE
            } else {
                binding.cvHolder.visibility = View.VISIBLE
                Glide.with(this)
                    .load(data.articles?.get(randomIndex)?.urlToImage)
                    .into(binding.ivHeadlineNews)
                binding.tvHeadlineTitle.text = data.articles?.get(randomIndex)?.title
                binding.tvHeadlineSource.text =data.articles?.get(randomIndex)?.source?.name
                binding.tvHeadlineTanggal.text = HelperConvertor().convertDayMonthYear(data.articles?.get(randomIndex)?.publishedAt.toString() ?: "10-10-2005")
                binding.cvHolder.setOnClickListener {
                    val i = Intent(Intent.ACTION_VIEW, data.articles?.get(randomIndex)?.url?.toUri())
                    startActivity(i)
                }
            }

        }

    }

    private fun setUpRecycleView(data: List<ArticlesNewsItem?>?){
        val layoutManager = LinearLayoutManager(this)

        binding.rvAllNews.layoutManager = layoutManager
        val newsAdapter = NewsListAdaptor()
        binding.rvAllNews.adapter =newsAdapter
        newsAdapter.submitList(data)


    }

    private fun getNewsList(){
        viewModel.getNewsList()

        viewModel.newsListResponse.observe(this){data ->
            setUpRecycleView(data.articles)
        }

    }


}