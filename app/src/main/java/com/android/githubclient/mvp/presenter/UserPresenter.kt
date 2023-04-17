package com.android.githubclient.mvp.presenter

import android.util.Log
import com.android.githubclient.mvp.model.GithubUsersRepo
import com.android.githubclient.mvp.view.UserView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UserPresenter(
    val userId: Int,
    val user: GithubUsersRepo,
    val router: Router,
) :
    MvpPresenter<UserView>() {

    companion object {
        private const val TAG = "UserPresenter"
    }

    private lateinit var userDisposable: Disposable

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    private fun loadData() {
        userDisposable = user.getUserById(userId)
            .subscribe(
                { user -> viewState.updateList(user) },
                { throwable -> Log.e(TAG, throwable.stackTraceToString()) }
            )

    }

    fun backPressed(): Boolean {
        userDisposable.dispose()
        router.exit()
        return true
    }

}