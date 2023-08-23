import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WanTabRow(pagerState: PagerState, titles: List<String>, modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
        indicator = { tabPositions ->
            Box(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                    .height(3.dp)
                    .clip(RoundedCornerShape(topStart = 3.dp, topEnd = 3.dp))
                    .background(MaterialTheme.colorScheme.primary)
            )
        },
    ) {
        titles.forEachIndexed { index, title ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = { Text(text = title, style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primary)) },
            )
        }
    }
}