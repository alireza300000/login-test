package com.alireza.loginappproject.View

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.alireza.loginappproject.androidWrapper.ActUtils
import com.alireza.loginappproject.databinding.ActivityMainBinding
import com.alireza.loginappproject.remote.RetrofitService
import com.alireza.loginappproject.remote.dataModel.DefaultModel
import com.alireza.loginappproject.remote.dataModel.GetApiModel
import com.alireza.loginappproject.remote.ext.ErrorUtils
import com.alireza.loginappproject.ui.HomeActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("ViewConstructor")
class ViewMainActivity(
    private val context: Context,
    private val utils: ActUtils
) {

    val b = ActivityMainBinding.inflate(LayoutInflater.from(context))

    fun onClickHandler(androidId: String) {
        b.btnSend.setOnClickListener {
            val email = b.edtInputEmail.text.toString()
            if (email.isEmpty()) {
                b.textInputEmail.error = "Email Empty"
                return@setOnClickListener
            }
            sendCodeInEmail(email)
            b.btnSend.visibility = View.INVISIBLE
            b.textInputEmail.visibility = View.INVISIBLE

            b.txtSendEmail.visibility = View.VISIBLE
            b.textInputCode.visibility = View.VISIBLE
            b.btnConfirm.visibility = View.VISIBLE
            b.txtWrong.visibility = View.VISIBLE

            b.txtSendEmail.text = "Send code to email :$email"
        }

        b.txtWrong.setOnClickListener {
            b.btnSend.visibility = View.VISIBLE
            b.textInputEmail.visibility = View.VISIBLE

            b.txtSendEmail.visibility = View.INVISIBLE
            b.textInputCode.visibility = View.INVISIBLE
            b.btnConfirm.visibility = View.INVISIBLE
            b.txtWrong.visibility = View.INVISIBLE
        }

        b.btnConfirm.setOnClickListener {
            val code = b.edtCode.text.toString()
            val email = b.edtInputEmail.text.toString()

            if (code.length < 6) {
                b.textInputCode.error = "Error"
                return@setOnClickListener
            } else {
                b.textInputCode.error = null
                verifyCodeEmail(androidId, code, email)
            }
        }
    }

    private fun sendCodeInEmail(email: String) {
        val service = RetrofitService.apiService
        CoroutineScope(Dispatchers.IO).launch {
            try {


                val response = service.sendRequest(email)
                if (response.isSuccessful) {
                    launch(Dispatchers.Main) {
                        val data = response.body() as DefaultModel
                        Toast.makeText(
                            context, data.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    launch(Dispatchers.Main) {
                        Toast.makeText(
                            context,
                            ErrorUtils.getError(response),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (e: Exception) {
                Log.i("SERVER_ERROR", e.message.toString())
            }
        }
    }


    private fun verifyCodeEmail(androidId: String, code: String, email: String) {


        val service = RetrofitService.apiService

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.verifyRequest(androidId, code, email)
                if (response.isSuccessful) {
                    launch(Dispatchers.Main) {
                        val data = response.body() as GetApiModel
                        data.api
                        Toast.makeText(
                            context, "لاگین شما موفقیت امیز بود",
                            Toast.LENGTH_SHORT
                        ).show()
                        context.startActivity(Intent(context, HomeActivity::class.java))
                        utils.finished()
                    }
                } else {
                    launch(Dispatchers.Main) {
                        Toast.makeText(
                            context,
                            ErrorUtils.getError(response),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            } catch (e: Exception) {
                Log.i("SERVER_ERROR", e.message.toString())
            }
        }

    }


}