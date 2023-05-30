package com.android.githubclient.di.repository

import com.android.githubclient.di.repository.module.RepositoryModule
import com.android.githubclient.ui.presenters.RepositoriesPresenter
import dagger.Subcomponent

@RepositoryScope
@Subcomponent(
    modules = [
        RepositoryModule::class]
)

interface RepositorySubcomponent {
    fun inject(repositoryPresenter: RepositoriesPresenter)
}