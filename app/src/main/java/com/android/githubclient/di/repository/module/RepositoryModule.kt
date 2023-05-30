package com.android.githubclient.di.repository.module

import com.android.githubclient.App
import com.android.githubclient.di.repository.IRepositoryScopeContainer
import com.android.githubclient.di.repository.RepositoryScope
import com.android.githubclient.domain.api.IDataSource
import com.android.githubclient.domain.cache.IGithubRepositoriesCache
import com.android.githubclient.domain.cache.room.RoomGithubRepositoriesCache
import com.android.githubclient.domain.network.INetworkStatus
import com.android.githubclient.domain.repo.retrofit.IGithubRepositoriesRepo
import com.android.githubclient.domain.repo.retrofit.RetrofitGithubRepositoriesRepo
import dagger.Module
import dagger.Provides

@Module
open class RepositoryModule {
    @Provides
    fun repositoriesCache(): IGithubRepositoriesCache {
        return RoomGithubRepositoriesCache()
    }

    @RepositoryScope
    @Provides
    open fun repositoriesRepo(
        api: IDataSource,
        networkStatus: INetworkStatus
    ): IGithubRepositoriesRepo {
        return RetrofitGithubRepositoriesRepo(api, networkStatus)
    }

    @RepositoryScope
    @Provides
    open fun scopeContainer(app: App): IRepositoryScopeContainer = app
}