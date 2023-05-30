package com.android.githubclient.domain.repo.retrofit

import com.android.githubclient.App
import com.android.githubclient.domain.api.IDataSource
import com.android.githubclient.domain.cache.room.RoomGithubUsersCache
import com.android.githubclient.domain.network.INetworkStatus
import com.android.githubclient.domain.repo.IGithubUsersRepo
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RetrofitGithubUsersRepo(
    val api: IDataSource, val networkStatus: INetworkStatus
) : IGithubUsersRepo {
@Inject
lateinit var userCache: RoomGithubUsersCache


    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        App.instance.appComponent.inject(this)
        if (isOnline) {
            userCache.newData(api)
        } else {
            userCache.fromDataBaseData()
        }
    }.subscribeOn(Schedulers.io())
}
