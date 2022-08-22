package com.example.newsapp.news

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.databinding.ItemNewsBinding
import com.example.newsapp.network.ArticlesItem


class NewsAdapter : RecyclerView.Adapter<NewsAdapter.HomeViewHolder>() {
    private val listNews = ArrayList<ArticlesItem>()
    fun setNews(news: List<ArticlesItem>?) {
        if (news.isNullOrEmpty()) return
        this.listNews.clear()
        this.listNews.addAll(news)
    }

    inner class HomeViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: ArticlesItem) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(news.urlToImage)
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
                    .centerCrop()
                    .into(imageviewItemnewsPoster)
                textviewItemnewsTitle.text = news.title
                textviewItemnewsDesc.text = news.description
                cardviewItemNews.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_DETAIL, news)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
        HomeViewHolder(
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val news = listNews[position]
        holder.bind(news)
    }

    override fun getItemCount(): Int = listNews.size

}