package com.android.githubclient.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.githubclient.App
import com.android.githubclient.mvp.model.api.ApiHolder
import com.android.githubclient.mvp.model.cache.room.RoomGithubRepositoriesCache
import com.android.githubclient.databinding.FragmentUserBinding
import com.android.githubclient.mvp.model.entity.GithubUser
import com.android.githubclient.mvp.model.entity.room.Database
import com.android.githubclient.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo
import com.android.githubclient.mvp.presenter.UserPresenter
import com.android.githubclient.mvp.view.UserView
import com.android.githubclient.ui.activity.BackButtonListener
import com.android.githubclient.ui.adapter.ReposRVAdapter
import com.android.githubclient.ui.network.AndroidNetworkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

@Suppress("DEPRECATION")
class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    companion object {
        private const val USER_ARG = "user"

        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
        }
    }

    val presenter: UserPresenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER_ARG) as GithubUser
        UserPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitGithubRepositoriesRepo(ApiHolder.api, AndroidNetworkStatus(App.instance), RoomGithubRepositoriesCache(Database.getInstance())),
            user
        ).apply {
            App.instance.appComponent.inject(this)
        }
    }

    private var vb: FragmentUserBinding? = null

    var adapter: ReposRVAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentUserBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvRepositories?.layoutManager = LinearLayoutManager(context)
        adapter = ReposRVAdapter(presenter.repositoriesListPresenter)
        vb?.rvRepositories?.adapter = adapter
    }

    override fun setLogin(text: String) {
        TODO("Not yet implemented")
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun release() {
        TODO("Not yet implemented")
    }

    override fun backPressed() = presenter.backPressed()
}