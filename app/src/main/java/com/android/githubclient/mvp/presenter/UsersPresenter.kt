package com.android.githubclient.mvp.presenter

import com.android.githubclient.mvp.presenter.list.IUserListPresenter
import com.android.githubclient.mvp.model.GithubUsersRepo
import com.android.githubclient.mvp.model.entity.GithubUser
import com.android.githubclient.mvp.view.UsersView
import com.android.githubclient.mvp.view.list.IUserItemView
import com.android.githubclient.navigation.IScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UsersPresenter(
    val usersRepo: GithubUsersRepo,
    val router: Router,
    val screens: IScreens,
) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((IUserItemView) -> Unit)? = null
        override fun getCount() = users.size

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            user.login.let { view.setLogin(it) }
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.users[itemView.pos]
            router.navigateTo(screens.user(user))
        }
    }

    fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        viewState.release()
    }
}
