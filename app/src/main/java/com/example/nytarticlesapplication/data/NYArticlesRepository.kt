package com.example.nytarticlesapplication.data

import androidx.lifecycle.LiveData
import com.example.nytarticlesapplication.data.local.Articles
import com.example.nytarticlesapplication.data.local.ArticlesDataBase
import com.example.nytarticlesapplication.data.remote.NYArticleApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NYArticlesRepository @Inject constructor(
    db: ArticlesDataBase
): INYArticlesRepository {

    private val dataBase = db.articleDao

    override suspend fun loadArticles() {
        val result = NYArticleApi.retrofitService.getNYArticles()
        val domain = mapToDomain(result)
        dataBase.insertArticles(domain)
    }

    override fun getArticles():LiveData<List<Articles>>{
        return dataBase.getAllArticles()
    }
}