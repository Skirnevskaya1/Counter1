package com.android.githubclient.ui

import android.os.Bundle
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.android.githubclient.entity.GithubRepository
import com.android.githubclient.entity.GithubUser
import com.android.githubclient.ui.interfaces.IScreens
import com.android.githubclient.ui.repositories.CURRENT_USER
import com.android.githubclient.ui.repositories.RepositoriesFragment
import com.android.githubclient.ui.users.UsersFragment

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }

    override fun repositories(currentUser: GithubUser): Screen = FragmentScreen {
        RepositoriesFragment.newInstance(Bundle().apply {
            putParcelable(
                CURRENT_USER,
                currentUser
            )
        })
    }

    override fun aboutRepository(infoRepository: GithubRepository): Screen = FragmentScreen {
        AboutRepositoryFragment.newInstance(Bundle().apply {
            putParcelable(
                REPOSITORY,
                infoRepository
            )
        })
    }
}



