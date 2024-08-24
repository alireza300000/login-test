package com.alireza.loginappproject.remote.ext

import com.alireza.loginappproject.remote.dataModel.ErrorModel
import com.google.gson.Gson
import retrofit2.Response

object ErrorUtils {
    fun getError(response: Response<*>): String {

        var error: String? = null
        val errorBody = response.errorBody()
        if (errorBody != null) {
            error = Gson().fromJson(errorBody.string(), ErrorModel::class.java).message
        }

        return error?:"ارتباط با سرور امکان پذیر نبود"
    }
}