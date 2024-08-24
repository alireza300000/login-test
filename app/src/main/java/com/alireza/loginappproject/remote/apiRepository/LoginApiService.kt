package com.alireza.loginappproject.remote.apiRepository

import com.alireza.loginappproject.remote.dataModel.DefaultModel
import com.alireza.loginappproject.remote.dataModel.GetApiModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginApiService {

    @FormUrlEncoded
    @POST("email/login")
    suspend fun sendRequest(
        @Field("email") emailTest: String
    ): Response<DefaultModel>

    @FormUrlEncoded
    @POST("email/login/verify")
    suspend fun verifyRequest(
        @Header("app-device-uid")androidId:String,
        @Field("code") code: String,
        @Field("email") email: String
    ): Response<GetApiModel>



    @FormUrlEncoded
    @POST("product/add")
    suspend fun addProduct(
        @Header("app-device-uid")androidId:String,
        @Header("app-api-key")api:String,
        @Field("id") code: String,
    ): Response<GetApiModel>

}