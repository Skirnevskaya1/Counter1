package com.android.githubclient.domain.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}
