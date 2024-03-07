package com.example.nytarticlesapplication.data

import androidx.lifecycle.MutableLiveData
import com.example.nytarticlesapplication.BuildConfig
import com.example.nytarticlesapplication.data.local.ArticleDao
import com.example.nytarticlesapplication.data.local.Articles
import com.example.nytarticlesapplication.data.local.ArticlesDataBase
import com.example.nytarticlesapplication.data.local.Media
import com.example.nytarticlesapplication.data.local.MediaMetadata
import com.example.nytarticlesapplication.data.remote.ArticleResponse
import com.example.nytarticlesapplication.data.remote.NYArticleApi
import com.example.nytarticlesapplication.data.remote.NYArticlesApiService
import com.example.nytarticlesapplication.data.remote.Result
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
//import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NYArticlesRepositoryTest{

    @Mock
    private lateinit var db: ArticlesDataBase

    @Mock
    private lateinit var articleDao: ArticleDao

    @Mock
    private lateinit var retrofitService: NYArticlesApiService

    private lateinit var repository: NYArticlesRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        `when`(db.articleDao).thenReturn(articleDao)
        repository = NYArticlesRepository(db)
    }

    @Test
    fun loadArticles_insertsDataIntoDatabase() = runBlocking {
        // Mock response from the API
        val articles = listOf(
            Articles(
                id = 1,
                abstractArticle = "Mock Abstract",
                adxKeywords = "Mock Keywords",
                assetId = 456,
                byline = "Mock Byline",
                column = "Mock Column",
                desFacet = listOf("Mock Facet"),
                etaId = 789,
                geoFacet = listOf("Mock Geo Facet"),
                media = listOf(Media(
                        approvedForSyndication = 1,
                        caption = "Mock Caption",
                        copyRight = "Mock Copyright",
                        mediaMetaData = listOf(MediaMetadata(
                            format = "image/jpeg",
                            height = 100,
                            url = "https://example.com/image.jpg",
                            width = 200
                        )),
                        subType = "image",
                        type = "image"
                )),
                nyTDsection = "Mock Section",
                orgFacet = listOf("Mock Org Facet"),
                perFacet = listOf("Mock Per Facet"),
                publishedDate = "2024-03-08",
                section = "Mock Section",
                source = "Mock Source",
                subsection = "Mock Subsection",
                title = "Mock Title",
                type = "Mock Type",
                updated = "2024-03-09",
                uri = "https://example.com/article",
                url = "https://example.com/article",
                abstract = "Mock Abstract"
            ),
            Articles(
                id = 2,
                abstractArticle = "Mock Abstract",
                adxKeywords = "Mock Keywords",
                assetId = 456,
                byline = "Mock Byline",
                column = "Mock Column",
                desFacet = listOf("Mock Facet"),
                etaId = 789,
                geoFacet = listOf("Mock Geo Facet"),
                media = listOf(Media(
                    approvedForSyndication = 1,
                    caption = "Mock Caption",
                    copyRight = "Mock Copyright",
                    mediaMetaData = listOf(MediaMetadata(
                        format = "image/jpeg",
                        height = 100,
                        url = "https://example.com/image.jpg",
                        width = 200
                    )),
                    subType = "image",
                    type = "image"
                )),
                nyTDsection = "Mock Section",
                orgFacet = listOf("Mock Org Facet"),
                perFacet = listOf("Mock Per Facet"),
                publishedDate = "2024-03-08",
                section = "Mock Section",
                source = "Mock Source",
                subsection = "Mock Subsection",
                title = "Mock Title",
                type = "Mock Type",
                updated = "2024-03-09",
                uri = "https://example.com/article",
                url = "https://example.com/article",
                abstract = "Mock Abstract"
            ),
            Articles(
                id = 3,
                abstractArticle = "Mock Abstract",
                adxKeywords = "Mock Keywords",
                assetId = 456,
                byline = "Mock Byline",
                column = "Mock Column",
                desFacet = listOf("Mock Facet"),
                etaId = 789,
                geoFacet = listOf("Mock Geo Facet"),
                media = listOf(Media(
                    approvedForSyndication = 1,
                    caption = "Mock Caption",
                    copyRight = "Mock Copyright",
                    mediaMetaData = listOf(MediaMetadata(
                        format = "image/jpeg",
                        height = 100,
                        url = "https://example.com/image.jpg",
                        width = 200
                    )),
                    subType = "image",
                    type = "image"
                )),
                nyTDsection = "Mock Section",
                orgFacet = listOf("Mock Org Facet"),
                perFacet = listOf("Mock Per Facet"),
                publishedDate = "2024-03-08",
                section = "Mock Section",
                source = "Mock Source",
                subsection = "Mock Subsection",
                title = "Mock Title",
                type = "Mock Type",
                updated = "2024-03-09",
                uri = "https://example.com/article",
                url = "https://example.com/article",
                abstract = "Mock Abstract"
            )
        )
        `when`(retrofitService.getNYArticles(BuildConfig.API_KEY)).thenReturn(
            ArticleResponse(
                copyRight = "",
                numResults = 3,
                results = listOf(
                    Result(
                        id = 1,
                        abstractArticle = "Mock Abstract",
                        adxKeywords = "Mock Keywords",
                        assetId = 456,
                        byline = "Mock Byline",
                        column = "Mock Column",
                        desFacet = listOf("Mock Facet"),
                        etaId = 789,
                        geoFacet = listOf("Mock Geo Facet"),
                        media = listOf(com.example.nytarticlesapplication.data.remote.Media(
                            approvedForSyndication = 1,
                            caption = "Mock Caption",
                            copyRight = "Mock Copyright",
                            mediaMetaData = listOf(com.example.nytarticlesapplication.data.remote.MediaMetadata(
                                format = "image/jpeg",
                                height = 100,
                                url = "https://example.com/image.jpg",
                                width = 200
                            )),
                            subType = "image",
                            type = "image"
                        )),
                        nyTDsection = "Mock Section",
                        orgFacet = listOf("Mock Org Facet"),
                        perFacet = listOf("Mock Per Facet"),
                        publishedDate = "2024-03-08",
                        section = "Mock Section",
                        source = "Mock Source",
                        subsection = "Mock Subsection",
                        title = "Mock Title",
                        type = "Mock Type",
                        updated = "2024-03-09",
                        uri = "https://example.com/article",
                        url = "https://example.com/article",

                    ),
                    Result(
                        id = 2,
                        abstractArticle = "Mock Abstract",
                        adxKeywords = "Mock Keywords",
                        assetId = 456,
                        byline = "Mock Byline",
                        column = "Mock Column",
                        desFacet = listOf("Mock Facet"),
                        etaId = 789,
                        geoFacet = listOf("Mock Geo Facet"),
                        media = listOf(com.example.nytarticlesapplication.data.remote.Media(
                            approvedForSyndication = 1,
                            caption = "Mock Caption",
                            copyRight = "Mock Copyright",
                            mediaMetaData = listOf(com.example.nytarticlesapplication.data.remote.MediaMetadata(
                                format = "image/jpeg",
                                height = 100,
                                url = "https://example.com/image.jpg",
                                width = 200
                            )),
                            subType = "image",
                            type = "image"
                        )),
                        nyTDsection = "Mock Section",
                        orgFacet = listOf("Mock Org Facet"),
                        perFacet = listOf("Mock Per Facet"),
                        publishedDate = "2024-03-08",
                        section = "Mock Section",
                        source = "Mock Source",
                        subsection = "Mock Subsection",
                        title = "Mock Title",
                        type = "Mock Type",
                        updated = "2024-03-09",
                        uri = "https://example.com/article",
                        url = "https://example.com/article",

                        ),
                    Result(
                        id = 3,
                        abstractArticle = "Mock Abstract",
                        adxKeywords = "Mock Keywords",
                        assetId = 456,
                        byline = "Mock Byline",
                        column = "Mock Column",
                        desFacet = listOf("Mock Facet"),
                        etaId = 789,
                        geoFacet = listOf("Mock Geo Facet"),
                        media = listOf(com.example.nytarticlesapplication.data.remote.Media(
                            approvedForSyndication = 1,
                            caption = "Mock Caption",
                            copyRight = "Mock Copyright",
                            mediaMetaData = listOf(com.example.nytarticlesapplication.data.remote.MediaMetadata(
                                format = "image/jpeg",
                                height = 100,
                                url = "https://example.com/image.jpg",
                                width = 200
                            )),
                            subType = "image",
                            type = "image"
                        )),
                        nyTDsection = "Mock Section",
                        orgFacet = listOf("Mock Org Facet"),
                        perFacet = listOf("Mock Per Facet"),
                        publishedDate = "2024-03-08",
                        section = "Mock Section",
                        source = "Mock Source",
                        subsection = "Mock Subsection",
                        title = "Mock Title",
                        type = "Mock Type",
                        updated = "2024-03-09",
                        uri = "https://example.com/article",
                        url = "https://example.com/article",

                        )
                ),
                status = ""
            )
        )

        // Call the method under test
        repository.loadArticles()

        // Verify that the data is inserted into the database
        verify(db.articleDao).insertArticles(articles)
    }

    @Test
    fun getArticles_returnsLiveData() {
        // Mock the LiveData returned by the DAO
        val articlesLiveData = MutableLiveData<List<Articles>>()
        `when`(db.articleDao.getAllArticles()).thenReturn(articlesLiveData)

        // Call the method under test
        val result = repository.getArticles()

        // Verify that the returned LiveData is not null
        assertNotNull(result)

        // Verify that the returned LiveData is the same instance as the one returned by the DAO
        assertEquals(articlesLiveData, result)
    }
}