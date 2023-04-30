package com.android.githubclient.mvp.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}