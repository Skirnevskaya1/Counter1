package com.android.githubclient

import android.app.Application
import com.android.githubclient.navigation.AndroidScreens
import com.android.githubclient.navigation.IScreens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router
    val screens : IScreens = AndroidScreens()

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
