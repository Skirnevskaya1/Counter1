package com.android.githubclient.mvp.view

import com.android.githubclient.mvp.model.entity.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView : MvpView {
    fun init()
    fun setLogin(text: String)
    fun updateList(user: GithubUser?)
    fun release()
}