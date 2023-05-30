package com.android.githubclient.domain.cache

import com.android.githubclient.domain.api.IDataSource
import com.android.githubclient.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubUsersCache {
    fun newData(api: IDataSource): Single<List<GithubUser>>
    fun fromDataBaseData(): Single<List<GithubUser>>
}