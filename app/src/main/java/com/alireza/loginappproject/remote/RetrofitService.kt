package com.alireza.loginappproject.remote

import com.alireza.loginappproject.remote.apiRepository.LoginApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private const val baseUrl="https://learn.alirezaahmadi.info/api/v1/auth/"
    private val retrofit:Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService:LoginApiService= retrofit.create(LoginApiService::class.java)
}