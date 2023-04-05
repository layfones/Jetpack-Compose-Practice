package com.layfones.composewanandroid.ui.components

import android.text.Html
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import com.layfones.composewanandroid.data.services.model.Article

@Composable
fun PostItem(article: Article, modifier: Modifier) {
    Card(Modifier.padding(12.dp, 4.dp)) {
        Column(
            modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Row() {
                Text(text = article.getArticleAuthor())
                if (article.type == 1) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "置顶")
                }
                if (article.fresh) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "新")
                }
                if (article.tags.isNotEmpty()) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = article.tags[0].name)
                }
                if (article.tags.size == 2) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = article.tags[1].name)
                }
                Spacer(modifier = Modifier.weight(1F))
                Text(text = article.niceDate)
            }
            Row(Modifier.padding(0.dp, 12.dp)) {
                Text(
                    text = Html.fromHtml(article.title).toString(),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Row() {
                Text(text = article.superChapterName + '·' + article.chapterName)
            }
        }
    }
}