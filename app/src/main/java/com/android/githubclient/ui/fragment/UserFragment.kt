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

    private var _binding: FragmentUserBinding? = null
    private val binding
        get() = _binding!!

    val presenter: UserPresenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER_ARG) as GithubUser

        UserPresenter(user, AndroidSchedulers.mainThread(),
            RetrofitGithubRepositoriesRepo(ApiHolder.api,
                AndroidNetworkStatus(App.instance),
                RoomGithubRepositoriesCache(Database.getInstance())),
            App.instance.router,
            App.instance.screens
        )
    }

    var adapter: ReposRVAdapter? = null

    companion object {
        private const val USER_ARG = "user"

        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) =
        FragmentUserBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {
        binding.rvRepositories.layoutManager = LinearLayoutManager(context)
        adapter = ReposRVAdapter(presenter.repositoriesListPresenter)
        binding.rvRepositories.adapter = adapter
    }

    override fun setLogin(text: String) {
        binding.tvLogin.text = text
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun release() {
        // TODO:
    }

    override fun backPressed() = presenter.backPressed()
}