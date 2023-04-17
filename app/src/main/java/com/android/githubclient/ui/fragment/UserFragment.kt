package com.android.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.githubclient.App
import com.android.githubclient.mvp.model.GithubUsersRepo
import com.gb.githubclient.databinding.FragmentUserBinding
import com.android.githubclient.mvp.model.entity.GithubUser
import com.android.githubclient.mvp.presenter.UserPresenter
import com.android.githubclient.mvp.view.UserView
import com.android.githubclient.ui.activity.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

@Suppress("DEPRECATION")
class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {
    companion object {
        private const val USER_ID = "USER_ID"

        fun newInstance(userId: Int) = UserFragment().apply {
            val bundle = Bundle().apply {
                putInt(USER_ID, userId)
            }

            this.arguments = bundle
        }
    }

    private val presenter: UserPresenter by moxyPresenter {
        val userId = arguments?.get(USER_ID) as Int
        UserPresenter(userId, GithubUsersRepo(), App.instance.router)
    }
    private var _viewBinding: FragmentUserBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _viewBinding = FragmentUserBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    override fun init() {
        TODO("Not yet implemented")
    }

    override fun setLogin(text: String) {
        TODO("Not yet implemented")
    }

    override fun updateList(user: GithubUser?) {
        viewBinding.tvLogin.text = user?.login

    }

    override fun release() {
        TODO("Not yet implemented")
    }


    override fun backPressed() = presenter.backPressed()
}