package com.layfones.composewanandroid.ui.components

import android.text.Html
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.layfones.composewanandroid.data.services.model.Article
import com.layfones.composewanandroid.data.services.model.CollectEvent
import com.layfones.composewanandroid.R

@Composable
fun ExploreItem(article: Article, modifier: Modifier, onClick: (Article) -> Unit, onCollect:(CollectEvent)->Unit) {
    Card(Modifier.padding(12.dp, 4.dp)) {
        Column(
            modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Row() {
                Column() {
                    Text(text = article.getArticleAuthor(), Modifier.clickable { onClick(article) })
                    Text(text = article.niceDate)
                }
                Spacer(modifier = Modifier.weight(1F))
                FilledIconToggleButton(checked = article.collect, onCheckedChange = {
                    onCollect(CollectEvent(article.id, article.link, it))
                }){
                    if (article.collect) {
                        Icon(painter = painterResource(R.drawable.ic_collect), contentDescription = "")
                    } else {
                        Icon(painter = painterResource(R.drawable.ic_un_collect), contentDescription = "")
                    }
                }
            }
            Row(Modifier.padding(0.dp, 12.dp)) {
                Text(
                    text = Html.fromHtml(article.title).toString(),
                    style = MaterialTheme.typography.titleMedium
                )
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