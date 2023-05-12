package com.android.githubclient.di.module

import androidx.room.Room
import com.android.githubclient.App
import com.android.githubclient.mvp.model.cache.IGithubUsersCache
import com.android.githubclient.mvp.model.cache.room.RoomGithubUsersCache
import com.android.githubclient.mvp.model.entity.room.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {
    @Singleton
    @Provides
    fun database(app: App): Database =
        Room.databaseBuilder(app, Database::class.java, Database.DB_NAME)
            .build()

    @Singleton
    @Provides
    fun usersCahche(db: Database): IGithubUsersCache = RoomGithubUsersCache(db)
}