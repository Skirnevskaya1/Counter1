package com.android.githubclient.ui.presenters

import com.github.terrakok.cicerone.Router
import com.android.githubclient.ui.AndroidScreens
import com.android.githubclient.ui.interfaces.MainView
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter () :
    MvpPresenter<MainView>() {
    @Inject
    lateinit var router: Router
    override fun onFirstViewAttach () {
        super.onFirstViewAttach()
        router.replaceScreen(AndroidScreens().users())
    }

    fun backClicked () {
        router.exit()
    }
}
