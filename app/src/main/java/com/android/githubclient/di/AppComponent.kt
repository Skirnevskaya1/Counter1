package com.android.githubclient.di

import com.android.githubclient.di.module.*
import com.android.githubclient.mvp.presenter.MainPresenter
import com.android.githubclient.mvp.presenter.RepositoryPresenter
import com.android.githubclient.mvp.presenter.UserPresenter
import com.android.githubclient.mvp.presenter.UsersPresenter
import com.android.githubclient.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        ApiModule::class,
        RepoModule::class,
        CacheModule::class
    ]
)
interface
AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userPresenter: UserPresenter)
    fun inject(repositoryPresenter: RepositoryPresenter)
}