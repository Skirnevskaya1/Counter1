package com.android.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.githubclient.mvp.model.GithubUsersRepo
import com.android.githubclient.mvp.presenter.UsersPresenter
import com.android.githubclient.mvp.view.UsersView
import com.android.githubclient.App.Companion.instance
import com.android.githubclient.ui.activity.BackButtonListener
import com.android.githubclient.ui.adapter.UsersRVAdapter
import com.gb.githubclient.databinding.FragmentUsersBinding
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

        UsersPresenter(GithubUsersRepo(), instance.router, instance.screens)
    }

    var adapter: UsersRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) =
        FragmentUsersBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {
        binding.rvUsers.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        binding.rvUsers.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun release() {
        // TODO
    }

    override fun backPressed() = presenter.backPressed()

}