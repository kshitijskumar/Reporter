package com.example.reporter.ui.allnews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reporter.R
import com.example.reporter.databinding.ItemNewsArticleBinding
import com.example.reporter.model.responses.Articles

class AllNewsAdapter : ListAdapter<Articles, AllNewsAdapter.ArticlesViewHolder>(diffUtil) {

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Articles>() {
            override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Articles, newItem: Articles): Boolean {
                return oldItem.url == newItem.url
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsArticleBinding.inflate(inflater, parent, false)
        return ArticlesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        val item = getItem(position)
        holder.setupViews(item)
    }

    class ArticlesViewHolder(val binding: ItemNewsArticleBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setupViews(item : Articles) {
            loadImage(item.urlToImage ?: "")
            binding.tvNewsTitle.text = item.title
        }

        private fun loadImage(imgUrl: String) {
            Glide.with(binding.ivNewsImage)
                .load(imgUrl)
                .error(R.drawable.ic_news_image)
                .placeholder(R.drawable.ic_news_image)
                .into(binding.ivNewsImage)
        }
    }
}