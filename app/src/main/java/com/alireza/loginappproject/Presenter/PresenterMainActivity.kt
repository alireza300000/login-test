package com.alireza.loginappproject.Presenter

import com.alireza.loginappproject.Model.ModelMainActivity
import com.alireza.loginappproject.View.ViewMainActivity

class PresenterMainActivity(
    private val view:ViewMainActivity,
    private val model:ModelMainActivity
) {
    fun onCreatePresenter()
    {
        view.onClickHandler(model.getId())

    }
}