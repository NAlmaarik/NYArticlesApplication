package com.example.nytarticlesapplication.data

import androidx.lifecycle.LiveData
import com.example.nytarticlesapplication.data.local.Articles
import javax.inject.Singleton

@Singleton
interface INYArticlesRepository {

    suspend fun loadArticles()

    fun getArticles(): LiveData<List<Articles>>
}