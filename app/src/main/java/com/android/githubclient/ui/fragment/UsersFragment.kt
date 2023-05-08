package com.android.githubclient.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.githubclient.App
import com.android.githubclient.mvp.model.api.ApiHolder
import com.android.githubclient.mvp.model.cache.room.RoomGithubUsersCache
import com.android.githubclient.mvp.model.entity.room.Database
import com.android.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import com.android.githubclient.mvp.presenter.UsersPresenter
import com.android.githubclient.mvp.view.UsersView
import com.android.githubclient.ui.activity.BackButtonListener
import com.android.githubclient.ui.adapter.UsersRVAdapter
import com.android.githubclient.ui.image.GlideImageLoader
import com.android.githubclient.ui.network.AndroidNetworkStatus
import com.android.githubclient.databinding.FragmentUsersBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    private var _binding: FragmentUsersBinding? = null
    private val binding
        get() = _binding!!

    companion object {
        fun newInstance() = UsersFragment()
    }

    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitGithubUsersRepo(ApiHolder.api,
                AndroidNetworkStatus(App.instance),
                RoomGithubUsersCache(Database.getInstance())),
            App.instance.router, App.instance.screens
        )
    }

    var adapter: UsersRVAdapter? = null
    private var vb: FragmentUsersBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) =
        FragmentUsersBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        binding.rvUsers.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        binding.rvUsers.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun release() {
        TODO("Not yet implemented")
    }

    override fun backPressed() = presenter.backPressed()
}