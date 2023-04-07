package com.android.githubclient.navigation

import com.android.githubclient.mvp.model.entity.GithubUser
import com.android.githubclient.ui.fragment.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun user(user: GithubUser) = FragmentScreen { UsersFragment.newInstance() }
}