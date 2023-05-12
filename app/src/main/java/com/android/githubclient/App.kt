package com.android.githubclient

import android.app.Application
import com.android.githubclient.di.AppComponent
import com.android.githubclient.di.DaggerAppComponent
import com.android.githubclient.di.module.AppModule
import com.android.githubclient.mvp.model.entity.room.Database

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        Database.create(this)

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}
