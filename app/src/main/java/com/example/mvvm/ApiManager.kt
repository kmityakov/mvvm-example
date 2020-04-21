package com.example.mvvm

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiService {
    @GET("search")
    fun search(
        @Query("term") term: String,
        @Query("limit") limit: Int
    ): Call<Response>

}

object ApiManager {
    const val BASE_URL = "https://itunes.apple.com/"

    lateinit var service: ApiService
    private lateinit var retrofit: Retrofit

    init {
        createAdapterAndService()
    }

    private fun createAdapterAndService() {
        retrofit = createJsonAdapter(BASE_URL)
        service = retrofit.create(ApiService::class.java)
    }

    private fun createJsonAdapter(baseUrl: String): Retrofit {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(20, TimeUnit.SECONDS)
        builder.callTimeout(20, TimeUnit.SECONDS)

        val gson = GsonBuilder()
            .create()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(builder.build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}