package com.android.githubclient

import android.app.Application
import com.android.githubclient.di.DaggerAppComponent
import com.android.githubclient.di.AppComponent
import com.android.githubclient.di.modules.AppModule
import com.android.githubclient.di.repository.IRepositoryScopeContainer
import com.android.githubclient.di.repository.RepositorySubcomponent
import com.android.githubclient.di.user.IUserScopeContainer
import com.android.githubclient.di.user.UserSubcomponent

class App : Application(), IUserScopeContainer, IRepositoryScopeContainer {

    companion object {
        lateinit var instance: App
        var appInstance: App? =
            null  // Оставил для вызова App.appInstance?.applicationContext из любого места программы
    }

    lateinit var appComponent: AppComponent
        private set
    var userSubcomponent: UserSubcomponent? = null
        private set
    var repositorySubcomponent: RepositorySubcomponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun initUserSubcomponent() = appComponent.userSubcomponent().also {
        userSubcomponent = it
    }

    fun initRepositorySubcomponent() =
        userSubcomponent?.repositorySubcomponent().also {
            repositorySubcomponent = it
        }

    override fun releaseUserSubComponent() {
        userSubcomponent = null
    }

    override fun releaseRepositorySubComponent() {
        repositorySubcomponent = null
    }
}