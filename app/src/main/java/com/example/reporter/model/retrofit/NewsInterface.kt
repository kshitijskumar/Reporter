package com.example.reporter.model.retrofit

import com.example.reporter.model.responses.NewsResponseBody
import com.example.reporter.utils.Constants.NEWS_API
import com.example.reporter.utils.Constants.NEWS_BASE_URL
import kotlinx.coroutines.internal.synchronized
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsInterface {

    @GET("{type}?country=in&apiKey=$NEWS_API&pageSize=100")
    suspend fun getNews(
        @Path("type") type: String
    ) : Response<NewsResponseBody>

    companion object {

        private val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        private val okHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(logging)
            .build()

        private val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(NEWS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        @Volatile
        private var INSTANCE : NewsInterface? = null

        fun getInstance() : NewsInterface {
            var localRef = INSTANCE

            return if(localRef != null) {
                localRef
            }else{
                localRef = retrofit.create(NewsInterface::class.java)
                INSTANCE = localRef
                localRef
            }


        }
    }
}