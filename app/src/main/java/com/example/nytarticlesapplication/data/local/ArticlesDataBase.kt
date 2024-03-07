package com.example.nytarticlesapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Articles::class], version = 1, exportSchema = false)
@TypeConverters(CachedStringConverter::class, CachedMediaConverter::class)
abstract class ArticlesDataBase() : RoomDatabase() {

    abstract val articleDao : ArticleDao

    companion object {
        const val DB_NAME = "articles_database"
    }
}