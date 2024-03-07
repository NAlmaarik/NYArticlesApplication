package com.example.nytarticlesapplication.di

import android.content.Context
import androidx.room.Room
import com.example.nytarticlesapplication.data.local.ArticlesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NYArticleDatabaseModule {

    @Singleton
    @Provides
    fun provideArticleDataBase( @ApplicationContext context: Context) : ArticlesDataBase{
        return Room.databaseBuilder(
            context,
            ArticlesDataBase::class.java,
            ArticlesDataBase.DB_NAME
        ).fallbackToDestructiveMigration().build()
    }
}