package com.alireza.loginappproject.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alireza.loginappproject.Model.ModelMainActivity
import com.alireza.loginappproject.Presenter.PresenterMainActivity
import com.alireza.loginappproject.R
import com.alireza.loginappproject.View.ViewMainActivity
import com.alireza.loginappproject.androidWrapper.ActUtils

class MainActivity : AppCompatActivity(),ActUtils {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val view=ViewMainActivity(this,this)
        setContentView(view.b.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val presenter =PresenterMainActivity(view, ModelMainActivity(this))
        presenter.onCreatePresenter()
    }

    override fun finished() {
        finish()
    }
}