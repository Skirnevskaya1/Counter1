package com.android.githubclient.domain.repo.retrofit

import com.android.githubclient.entity.GithubRepository
import com.android.githubclient.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubRepositoriesRepo {
fun getRepositories (currentUser: GithubUser): Single<List<GithubRepository>>
}
