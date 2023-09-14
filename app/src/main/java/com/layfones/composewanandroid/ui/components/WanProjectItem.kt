package com.layfones.composewanandroid.ui.components

import android.text.Html
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.layfones.composewanandroid.data.services.model.Article

@Composable
fun WanProjectItem(article: Article, modifier: Modifier = Modifier) {
    Card(Modifier
        .padding(12.dp, 4.dp)
        .height(173.dp)) {
        Row(modifier
            .padding(8.dp)
            .fillMaxSize()) {
            Card(modifier = Modifier
                .width(104.dp)
                .height(165.dp),
                shape = RoundedCornerShape(6.dp)) {
                AsyncImage(model = article.envelopePic,
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize())
            }
            Column(modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)) {
                Text(text = Html.fromHtml(article.title).toString(),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2)
                Text(modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 6.dp),
                    overflow = TextOverflow.Ellipsis,
                    text = article.desc)
                Row() {
                    Text(text = article.niceDate)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = article.getArticleAuthor())
                }
            }
        }
    }
}