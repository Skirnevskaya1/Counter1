package com.android.githubclient.mvp.presenter

import android.util.Log
import com.android.githubclient.mvp.presenter.list.IUserListPresenter
import com.android.githubclient.mvp.model.GithubUsersRepo
import com.android.githubclient.mvp.model.entity.GithubUser
import com.android.githubclient.mvp.view.UsersView
import com.android.githubclient.mvp.view.list.IUserItemView
import com.android.githubclient.navigation.IScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UsersPresenter(
    val usersRepo: GithubUsersRepo,
    val router: Router,
    val screens: IScreens,
) : MvpPresenter<UsersView>() {

    companion object {
        private const val TAG = "UsersPresenter"
    }

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    private lateinit var usersDisposable: Disposable

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.users[itemView.pos]
            router.navigateTo(screens.user(user.id))
        }
    }

    private fun loadData() {
        usersDisposable = usersRepo.getUsers()
            .subscribe(
                { users ->
                    usersListPresenter.users.addAll(users)
                    viewState.updateList()
                },
                { throwable -> Log.e(TAG, throwable.stackTraceToString()) }
            )
    }

    fun backPressed(): Boolean {
        usersDisposable.dispose()
        router.exit()
        return true
    }

}