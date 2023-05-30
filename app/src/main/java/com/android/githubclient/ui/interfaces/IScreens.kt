package com.android.githubclient.ui.interfaces

import com.github.terrakok.cicerone.Screen
import com.android.githubclient.entity.GithubRepository
import com.android.githubclient.entity.GithubUser

interface IScreens {
    fun users(): Screen
    fun aboutRepository(infoRepository: GithubRepository): Screen
    fun repositories(currentUser: GithubUser): Screen
}
