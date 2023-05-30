package com.android.githubclient.di.user

import com.android.githubclient.di.repository.RepositorySubcomponent
import com.android.githubclient.di.user.module.UserModule
import com.android.githubclient.ui.presenters.UsersPresenter
import com.android.githubclient.ui.users.UsersRVAdapter
import dagger.Subcomponent

@UserScope
@Subcomponent(
    modules = [
        UserModule::class
    ]
)
interface UserSubcomponent {
   fun repositorySubcomponent(): RepositorySubcomponent
    fun inject(usersPresenter: UsersPresenter)
    fun inject(usersRVAdapter: UsersRVAdapter)
}