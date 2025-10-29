package my.id.zaxx.news.Adaptor

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import my.id.zaxx.news.data.api.response.ArticlesNewsItem
import my.id.zaxx.news.databinding.ItemNewsHolderBinding
import my.id.zaxx.news.util.HelperConvertor
import androidx.core.net.toUri

class NewsListAdaptor : ListAdapter<ArticlesNewsItem?, NewsListAdaptor.ViewHolder>(DIFF_CALLBACK) {

    val newsList: List<ArticlesNewsItem?>? = emptyList()
    class ViewHolder(private val binding: ItemNewsHolderBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: ArticlesNewsItem?){
            binding.root.setOnClickListener {
                val i = Intent(Intent.ACTION_VIEW, data?.url?.toUri())
                binding.root.context.startActivity(i)
            }

            binding.tvTitleNews.text = data?.title
            binding.tvDescription.text = data?.description
            binding.tvSourceNews.text = data?.source?.name
            binding.tvTanggal.text = HelperConvertor().convertDayMonthYear(data?.publishedAt.toString())
            Glide.with(binding.root.context)
                .load(data?.urlToImage.toString())
                .into(binding.imgNews)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsListAdaptor.ViewHolder {
        val binding= ItemNewsHolderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = getItem(position)
        holder.bind(item)

    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArticlesNewsItem?>() {
            override fun areItemsTheSame(
                oldItem: ArticlesNewsItem,
                newItem: ArticlesNewsItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ArticlesNewsItem,
                newItem: ArticlesNewsItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

}