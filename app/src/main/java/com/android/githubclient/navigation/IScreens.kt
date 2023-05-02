package com.android.githubclient.navigation

import com.android.githubclient.mvp.model.entity.GithubRepository
import com.android.githubclient.mvp.model.entity.GithubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun user(user: GithubUser): Screen
    fun repository(repo: GithubRepository): Screen
}
