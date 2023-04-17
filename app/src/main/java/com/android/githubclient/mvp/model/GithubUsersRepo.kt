package com.android.githubclient.mvp.model

import com.android.githubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class GithubUsersRepo {
    private val repositories = Observable.create<List<GithubUser>> { emitter ->
        val users = (1..100).map { GithubUser(it, "User $it") }
        emitter.onNext(users)
    }.observeOn(Schedulers.computation())

    fun getUsers(): Observable<List<GithubUser>> =
        repositories.observeOn(AndroidSchedulers.mainThread())

    fun getUserById(id: Int): Observable<GithubUser> = repositories.map { users ->
        users.first { it.id == id }
    }.subscribeOn(AndroidSchedulers.mainThread())
}