package com.example.nytarticlesapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nytarticlesapplication.ui.theme.NYTArticlesApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: NYArticleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NYTArticlesApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }

        viewModel.articles.observe(this, Observer {
            Log.i("check work", "$it")
        })

    }
}

@Composable
fun MyApp() {
    val scaffoldState = rememberScaffoldState()
    val viewModel: NYArticleViewModel = viewModel()
    val articles = viewModel.articles
    val loading by viewModel.loadingStatus.observeAsState(initial = true)
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            if (currentRoute != "articlesList") {
                TopAppBar(
                    title = { Text(text = "NYArticleDetails") },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }else{
                TopAppBar(
                    title = { Text(text = "NYArticles") },
                )
            }
        },
        content = {paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)){
                NavHost(navController = navController, startDestination = "articlesList") {
                    composable("articlesList") {
                        if (loading) {
                            LoadingScreen()
                        } else
                            NYArticlesListScreen(navController, articles.value)
                    }
                    composable("articleDetails/{articleId}") {backStackEntry ->
                        val id = backStackEntry.arguments?.getString("articleId")?.toLong()
                        val item = articles.value?.find { it.id == id }
                        NYArticleDetailsScreen(item)
                    }

                }
            }
        }
    )

}

@Composable
fun LoadingScreen() {
    // Display a loading indicator while data is being loaded
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}


@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    NYTArticlesApplicationTheme {
        MyApp()
    }
}