package com.android.githubclient.mvp.model.repo.retrofit

import com.android.githubclient.mvp.model.api.IDataSource
import com.android.githubclient.mvp.model.entity.GithubUser
import com.android.githubclient.mvp.model.repo.IGithubRepositoriesRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubRepositoriesRepo(val api: IDataSource) : IGithubRepositoriesRepo {
    override fun getRepositories(user: GithubUser) = user.reposUrl?.let {
        api.getRepositories(user.reposUrl.toString()).subscribeOn(Schedulers.io())
    }
        ?: Single.error(RuntimeException("User has no repos url"))
}