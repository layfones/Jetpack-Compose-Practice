package com.layfones.composewanandroid.ui.components

import android.text.Html
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.layfones.composewanandroid.R
import com.layfones.composewanandroid.data.services.model.Article

@Composable
fun ProjectItem(article: Article, modifier: Modifier = Modifier) {
    Card(
        Modifier
            .padding(12.dp, 4.dp)
            .height(173.dp)) {
        Row(
            modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            Card(modifier = Modifier
                .width(104.dp)
                .height(165.dp)) {
                val url = article.envelopePic
                Log.d("他妈的", article.title +"---" +url)
                // Image(painter = rememberAsyncImagePainter(url), contentDescription = null, modifier = Modifier.fillMaxSize())
                AsyncImage(model = article.envelopePic, contentScale = ContentScale.Crop, contentDescription = null, modifier = Modifier.fillMaxSize())
            }
            Column(modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)) {
                Text(text = Html.fromHtml(article.title).toString(),overflow = TextOverflow.Ellipsis, maxLines = 2)
                Text(modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 6.dp), overflow = TextOverflow.Ellipsis, text = article.desc)
                Row() {
                    Text(text = article.niceDate)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = article.getArticleAuthor())
                }
            }
        }
    }
}