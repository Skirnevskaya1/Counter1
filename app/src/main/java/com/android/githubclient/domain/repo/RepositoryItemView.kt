package com.android.githubclient.domain.repo

interface RepositoryItemView {
    var pos: Int
}
interface ReposItemView : RepositoryItemView {
    fun setName (text: String? )
}