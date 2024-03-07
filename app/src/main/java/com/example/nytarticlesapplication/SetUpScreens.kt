package com.example.nytarticlesapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.nytarticlesapplication.data.local.Articles


@Composable
fun NYArticlesListScreen(navController: NavController, articleLists: List<Articles>?) {
    LazyColumn {
        articleLists?.let {
            items(it) { item ->
                NYArticleItemScreen(
                    item,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clickable {
                            navController.navigate("articleDetails/${item.id}")
                        })
            }
        }
    }
}

@Composable
fun NYArticleItemScreen(item: Articles, modifier: Modifier) {
    Card(
        modifier = modifier
            .padding(all = 8.dp)
            .fillMaxWidth(), shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = modifier
                .padding(all = 8.dp)
                .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(
                    data = item.media?.getOrNull(0)?.mediaMetaData?.getOrNull(0)?.url,
                    builder = {
                        placeholder(R.drawable.ic_article_placeholder)
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = item.title.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                // Add a vertical space between the author and message texts
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = item.subsection.toString(), style = MaterialTheme.typography.bodySmall)
            }

        }
    }
}

@Composable
fun NYArticleDetailsScreen(item: Articles?) {
    Card(
        modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxWidth(), shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(all = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = rememberImagePainter(
                    data = item?.media?.getOrNull(0)?.mediaMetaData?.getOrNull(0)?.url,
                    builder = {
                        placeholder(R.drawable.ic_article_placeholder)
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(32.dp))

            Text(text = item?.title.toString(), style = MaterialTheme.typography.titleLarge)
            // Add a vertical space between the author and message texts
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = item?.abstractArticle.toString(),
                style = MaterialTheme.typography.bodyMedium
            )

        }
    }
}
