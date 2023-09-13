package com.layfones.composewanandroid.ui.components

import android.text.Html
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.layfones.composewanandroid.R
import com.layfones.composewanandroid.data.services.model.Article
import com.layfones.composewanandroid.data.services.model.CollectEvent

@Composable
fun ExploreItem(article: Article,
                index: Int,
                modifier: Modifier = Modifier,
                onItemClick: (Article) -> Unit,
                onAuthorNameClick: (Long) -> Unit,
                onCollectionClick: (CollectEvent) -> Unit) {
    var collect by remember { mutableStateOf(article.collect) }
    Card(Modifier.padding(12.dp, 4.dp)) {
        Column(modifier
            .padding(16.dp)
            .fillMaxSize()
            .clickable {
                onItemClick(article)
            }
        ) {
            Row() {
                Column() {
                    Text(text = article.getArticleAuthor(),
                        Modifier.clickable { onAuthorNameClick(article.userId) })
                    Text(text = article.niceDate)
                }
                Spacer(modifier = Modifier.weight(1F))
                FilledIconToggleButton(checked = collect, onCheckedChange = {
                    onCollectionClick(CollectEvent(index, article.id, article.link, it))
                    collect = it
                }) {
                    Icon(painter = painterResource(if (collect) R.drawable.ic_collect else R.drawable.ic_un_collect),
                        contentDescription = "")
                }
            }
            Row(Modifier.padding(0.dp, 12.dp)) {
                Text(text = Html.fromHtml(article.title).toString(),
                    style = MaterialTheme.typography.titleMedium)
            }
            Row() {
                if (article.type == 1) {
                    Text(text = "置顶")
                    Spacer(modifier = Modifier.width(8.dp))
                }
                if (article.fresh) {
                    Text(text = "新")
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(text = article.superChapterName + '·' + article.chapterName)
            }
        }
    }
}