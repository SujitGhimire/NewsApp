package com.example.newsproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsproject.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getNews()
    }

    private fun getNews() {
        val news = NewsService.newsInstance.getHeadlines("us", 1)
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news:News? = response.body()
                if (news != null) {
                    Log.d("Chiplu", news.toString())
                    adapter= NewsAdapter(this@MainActivity,news.articles)
                    binding.recyclerView.adapter=adapter
                    binding.recyclerView.layoutManager=LinearLayoutManager(this@MainActivity)
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Chiplu", "Eroor in fetching", t)

            }

        })

    }
}