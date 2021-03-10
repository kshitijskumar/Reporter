package com.example.reporter.model.remotedatasource

import com.example.reporter.model.retrofit.NewsInterface

class NewsDataSource(
        private val api : NewsInterface
) : BaseDataSource() {

    suspend fun fetchNews(type: String) = safeApiRequest {
        api.getNews(type)
    }
}