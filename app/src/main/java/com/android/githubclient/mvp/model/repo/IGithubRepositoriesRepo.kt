package com.android.githubclient.mvp.model.repo

import com.android.githubclient.mvp.model.entity.GithubRepository
import com.android.githubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubRepositoriesRepo {
    fun getRepositories(user: GithubUser): Single<List<GithubRepository>>
}