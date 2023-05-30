package com.android.githubclient.ui.presenters

import com.android.githubclient.ui.users.IItemView
import com.android.githubclient.ui.users.UserItemView

interface IListPresenter <V : IItemView> {

    var itemClickListener: ((V) -> Unit )?
    fun bindView (view: V)
    fun getCount (): Int }

interface IUserListPresenter : IListPresenter<UserItemView>

