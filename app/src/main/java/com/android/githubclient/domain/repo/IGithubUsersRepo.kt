package com.android.githubclient.domain.repo

import com.android.githubclient.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubUsersRepo {
    fun getUsers (): Single<List<GithubUser>>
}
