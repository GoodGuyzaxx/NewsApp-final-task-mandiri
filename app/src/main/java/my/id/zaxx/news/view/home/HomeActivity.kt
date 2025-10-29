package my.id.zaxx.news.view.home

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import my.id.zaxx.news.R
import my.id.zaxx.news.databinding.ActivityHomeBinding


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getHeadlineData()
    }

    private fun getHeadlineData() {
        viewModel.getHeadlineNews()

        viewModel.headlineResponse.observe(this){data ->
            Glide.with(this)
                .load(data.articles?.get(0)?.urlToImage)
                .into(binding.ivHeadlineNews)
            binding.tvHeadlineTitle.text = data.articles?.get(0)?.title
            binding.tvHeadlineSource.text =data.articles?.get(0)?.source?.name
        }

    }

}