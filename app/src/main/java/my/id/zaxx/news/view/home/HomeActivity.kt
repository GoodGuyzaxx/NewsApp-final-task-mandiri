package my.id.zaxx.news.view.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.faltenreich.skeletonlayout.createSkeleton
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
//        binding.skLayoutContent.createSkeleton()

        getHeadlineData()
        getNewsList()
        
    }

    private fun getHeadlineData() {
        viewModel.getHeadlineNews()
        val randomIndex = (1..10).random()

        viewModel.headlineResponse.observe(this){data ->
            Glide.with(this)
                .load(data.articles?.get(randomIndex)?.urlToImage)
                .into(binding.ivHeadlineNews)
            binding.tvHeadlineTitle.text = data.articles?.get(randomIndex)?.title
            binding.tvHeadlineSource.text =data.articles?.get(randomIndex)?.source?.name
            binding.tvHeadlineTanggal.text = HelperConvertor().convertDayMonthYear(data.articles?.get(randomIndex)?.publishedAt.toString())
            binding.cvHolder.setOnClickListener {
                val i = Intent(Intent.ACTION_VIEW, data.articles?.get(randomIndex)?.url?.toUri())
                startActivity(i)
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

//        viewModel.isLoading.observe(this){loading ->
//            if (loading){
//                binding.skLayoutContent.showOriginal()
//            } else {
//                binding.skLayoutContent.showSkeleton()
//            }
//        }
    }


}