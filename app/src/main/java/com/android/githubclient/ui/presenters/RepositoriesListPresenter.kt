package com.android.githubclient.ui.presenters

import com.android.githubclient.domain.repo.ReposItemView
import com.android.githubclient.domain.repo.RepositoryItemView

interface RepositoriesListPresenter <V : RepositoryItemView> {

    var itemClickListener: ((V) -> Unit )?
    fun bindView (view: V)
    fun getCount (): Int }

interface RepositListPresenter : RepositoriesListPresenter<ReposItemView>
