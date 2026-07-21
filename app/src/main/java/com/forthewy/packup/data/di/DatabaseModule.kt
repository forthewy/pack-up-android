package com.forthewy.packup.data.di

import android.content.Context
import androidx.room.Room
import com.forthewy.packup.data.local.dao.CheckItemDao
import com.forthewy.packup.data.local.database.PackUpDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
    @ApplicationContext context: Context
    ): PackUpDatabase {
        return Room.databaseBuilder(
            context,
            PackUpDatabase::class.java,
            "packup_database"
        ).build()
    }

    @Provides
    fun provideCheckItemDao(
        database: PackUpDatabase
    ): CheckItemDao {
        return database.checkItemDao()
    }
}