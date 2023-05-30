package com.android.githubclient.di

import com.android.githubclient.di.modules.*
import com.android.githubclient.di.user.UserSubcomponent
import com.android.githubclient.domain.cache.room.RoomGithubPictureCache
import com.android.githubclient.domain.cache.room.RoomGithubRepositoriesCache
import com.android.githubclient.domain.cache.room.RoomGithubUsersCache
import com.android.githubclient.domain.repo.retrofit.RetrofitGithubRepositoriesRepo
import com.android.githubclient.domain.repo.retrofit.RetrofitGithubUsersRepo
import com.android.githubclient.ui.MainActivity
import com.android.githubclient.ui.presenters.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        DatabaseModule::class,
        ApiModule::class,
        ImageModule::class
    ]
)
interface AppComponent {
    fun userSubcomponent(): UserSubcomponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)

    fun inject(retrofitGithubUsersRepo: RetrofitGithubUsersRepo)
    fun inject(retrofitGithubRepositoriesRepo: RetrofitGithubRepositoriesRepo)
    fun inject(roomGithubUsersCache: RoomGithubUsersCache)
    fun inject(roomGithubRepositoriesCache: RoomGithubRepositoriesCache)
    fun inject(roomGithubPictureCache: RoomGithubPictureCache)

}