package com.example.newsproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(val context: Context,val articles:List<Article>):RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>(){


    inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var newsImage:ImageView=itemView.findViewById(R.id.image_news)
        var newsTitle:TextView=itemView.findViewById(R.id.title_news)
        var newsDescription:TextView=itemView.findViewById(R.id.description_news)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.newsTitle.text=articles[position].title
        holder.newsDescription.text=articles[position].description
        Glide.with(context).load(articles[position].urlToImage).into(holder.newsImage)
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}