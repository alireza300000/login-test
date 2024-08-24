package com.alireza.loginappproject.Model

import android.content.Context
import com.alireza.loginappproject.androidWrapper.DeviceInfo

class ModelMainActivity (private val context: Context){
    fun getId() = DeviceInfo.getAndroidId(context)
}