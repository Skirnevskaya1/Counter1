package com.android.githubclient.navigation

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun user(user: Int): Screen
}
