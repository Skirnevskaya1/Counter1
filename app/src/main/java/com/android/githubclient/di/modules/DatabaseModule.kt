package com.android.githubclient.di.modules

import androidx.room.Room
import com.android.githubclient.App
import com.android.githubclient.domain.cache.IGithubPictureCache
import com.android.githubclient.domain.cache.room.RoomGithubPictureCache
import com.android.githubclient.domain.cache.room.RoomGithubRepositoriesCache
import com.android.githubclient.domain.cache.room.RoomGithubUsersCache
import com.android.githubclient.entity.room.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun database(app: App): Database = Room.databaseBuilder(
        app, Database::class.java,
        Database.DB_NAME
    ).build()

    @Singleton
    @Provides
    fun roomGithubUsersCache(): RoomGithubUsersCache = RoomGithubUsersCache()

    @Singleton
    @Provides
    fun roomGithubRepositoriesCache(): RoomGithubRepositoriesCache = RoomGithubRepositoriesCache()

    @Singleton
    @Provides
    fun roomGithubPictureCache(): RoomGithubPictureCache = RoomGithubPictureCache()

    @Singleton
    @Provides
    fun pictureCache(): IGithubPictureCache = RoomGithubPictureCache()
}