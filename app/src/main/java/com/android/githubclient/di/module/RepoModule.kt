package com.android.githubclient.di.module

import com.android.githubclient.mvp.model.api.IDataSource
import com.android.githubclient.mvp.model.cache.IGithubRepositoriesCache
import com.android.githubclient.mvp.model.cache.IGithubUsersCache
import com.android.githubclient.mvp.model.network.INetworkStatus
import com.android.githubclient.mvp.model.repo.IGithubRepositoriesRepo
import com.android.githubclient.mvp.model.repo.IGithubUsersRepo
import com.android.githubclient.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo
import com.android.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {
    @Singleton
    @Provides
    fun usersRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IGithubUsersCache)
            : IGithubUsersRepo = RetrofitGithubUsersRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun repositoriesRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IGithubRepositoriesCache,
    )
            : IGithubRepositoriesRepo = RetrofitGithubRepositoriesRepo(api, networkStatus, cache)
}