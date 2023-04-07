package com.android.githubclient.mvp.presenter.list

import com.android.githubclient.mvp.view.list.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}