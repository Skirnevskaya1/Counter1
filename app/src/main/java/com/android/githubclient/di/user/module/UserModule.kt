package com.android.githubclient.di.user.module

import com.android.githubclient.App
import com.android.githubclient.di.user.IUserScopeContainer
import com.android.githubclient.di.user.UserScope
import com.android.githubclient.domain.api.IDataSource
import com.android.githubclient.domain.cache.IGithubUsersCache
import com.android.githubclient.domain.cache.room.RoomGithubUsersCache
import com.android.githubclient.domain.network.INetworkStatus
import com.android.githubclient.domain.repo.IGithubUsersRepo
import com.android.githubclient.domain.repo.retrofit.RetrofitGithubUsersRepo
import dagger.Module
import dagger.Provides

@Module
open class UserModule {
    @Provides
    fun usersCache(): IGithubUsersCache {
        return RoomGithubUsersCache()
    }
    @UserScope
    @Provides
    open fun usersRepo(api: IDataSource, networkStatus: INetworkStatus): IGithubUsersRepo {
        return RetrofitGithubUsersRepo(api, networkStatus)
    }
    @UserScope
    @Provides
    open fun scopeContainer(app: App): IUserScopeContainer = app
}