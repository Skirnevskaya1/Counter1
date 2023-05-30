package com.android.githubclient.domain.cache

import com.android.githubclient.domain.api.IDataSource
import com.android.githubclient.entity.GithubRepository
import com.android.githubclient.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubRepositoriesCache {
    fun newData(user: GithubUser, api: IDataSource): Single<List<GithubRepository>>
    fun fromDataBaseData(user: GithubUser): Single<List<GithubRepository>>
}