package com.android.githubclient.domain.repo.retrofit

import com.android.githubclient.App
import com.android.githubclient.domain.api.IDataSource
import com.android.githubclient.domain.cache.room.RoomGithubRepositoriesCache
import com.android.githubclient.domain.network.INetworkStatus
import com.android.githubclient.entity.GithubUser
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RetrofitGithubRepositoriesRepo(
    val api: IDataSource, val networkStatus:
    INetworkStatus
) : IGithubRepositoriesRepo {
@Inject
lateinit var repositoriesCache: RoomGithubRepositoriesCache

    override fun getRepositories(user: GithubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            App.instance.appComponent.inject(this)
            if (isOnline) {
                repositoriesCache.newData(user, api)
            } else {
                repositoriesCache.fromDataBaseData(user)
            }
        }.subscribeOn(Schedulers.io())
}
