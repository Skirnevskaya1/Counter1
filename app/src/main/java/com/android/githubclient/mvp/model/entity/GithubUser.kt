package com.android.githubclient.mvp.model.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class GithubUser(
    val login: String,
) : Parcelable