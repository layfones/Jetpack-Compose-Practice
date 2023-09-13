package com.layfones.composewanandroid.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.layfones.composewanandroid.data.services.model.Classify

@Composable
fun TutorialItem(tutorial: Classify) {
    Card(Modifier.padding(12.dp, 4.dp)) {
        Row(
            Modifier
                .padding(horizontal = 8.dp, vertical = 10.dp)
                .fillMaxSize()) {
            Card(
                modifier = Modifier
                    .width(72.dp)
                    .height(100.dp)
                    .align(Alignment.CenterVertically),
                shape = RoundedCornerShape(6.dp)
            ) {
                AsyncImage(
                    model = tutorial.cover,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(text = tutorial.name, style = MaterialTheme.typography.titleMedium)
                Text(text = tutorial.author, modifier = Modifier.padding(vertical = 6.dp))
                Text(text = tutorial.desc, maxLines = 3, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}