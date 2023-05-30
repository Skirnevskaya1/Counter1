package com.android.githubclient.domain.cache

import com.android.githubclient.entity.GithubPicture
import com.android.githubclient.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubPictureCache {
    fun newData(users: List<GithubUser>)
    fun fromDataBaseData(): Single<List<GithubPicture>>
}