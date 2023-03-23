package com.android.counter.mvp.presenter

import com.android.counter.mvp.presenter.list.IUserListPresenter
import com.android.counter.mvp.model.GithubUsersRepo
import com.android.counter.mvp.model.entity.GithubUser
import com.android.counter.mvp.view.UsersView
import com.android.counter.mvp.view.list.IUserItemView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UsersPresenter(val usersRepo: GithubUsersRepo, val router: Router) :
    MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((IUserItemView) -> Unit)? = null
        override fun getCount() = users.size

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.users[itemView.pos]

            // Navigate to next screen
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
