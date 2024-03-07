package com.example.nytarticlesapplication.di

import com.example.nytarticlesapplication.data.INYArticlesRepository
import com.example.nytarticlesapplication.data.NYArticlesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NYArticleRepoModule {

    @Binds
    @Singleton
    abstract fun provideNYArticleRepository(nyArticlesRepository: NYArticlesRepository): INYArticlesRepository
}