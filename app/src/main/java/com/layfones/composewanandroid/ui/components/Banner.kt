package com.layfones.composewanandroid.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.layfones.composewanandroid.data.services.model.Banner
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Banner(banners: List<Banner>, modifier: Modifier = Modifier, onItemClick: (Int) -> Unit) {
    Box(
        modifier
            .fillMaxSize()
    ) {
        val pageCount = banners.size
        val startIndex = Int.MAX_VALUE / 2
        val pagerState = rememberPagerState(initialPage = startIndex, pageCount = {pageCount})
        val coroutineScope = rememberCoroutineScope()
        DisposableEffect(Unit) {
            val timer = Timer()
            timer.schedule(object : TimerTask() {
                override fun run() {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            }, 2000, 2000)
            onDispose {
                timer.cancel()
            }
        }

        HorizontalPager(
            modifier = Modifier.height(200.dp),
            state = pagerState,
            pageSpacing = 4.dp,
        ) { index ->
            val page = (index - startIndex).floorMod(pageCount)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .height(180.dp)
                    .clickable {
                        onItemClick(page)
                    }
            ) {
                val url = banners[page].imagePath
                AsyncImage(
                    model = url,
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.matchParentSize()
                )
            }
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            count = pageCount,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(0.dp, 12.dp)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerIndicator(
    pagerState: PagerState,
    count: Int,
    modifier: Modifier = Modifier,
    activeColor: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
    inactiveColor: Color = activeColor.copy(ContentAlpha.disabled),
    indicatorWidth: Dp = 8.dp,
    indicatorHeight: Dp = indicatorWidth,
    spacing: Dp = indicatorWidth,
    indicatorShape: Shape = CircleShape,
) {

    val indicatorWidthPx = LocalDensity.current.run { indicatorWidth.roundToPx() }
    val spacingPx = LocalDensity.current.run { spacing.roundToPx() }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val indicatorModifier = Modifier
                .size(width = indicatorWidth, height = indicatorHeight)
                .background(color = inactiveColor, shape = indicatorShape)
            repeat(count) {
                Box(indicatorModifier)
            }
        }

        Box(
            Modifier
                .offset {
                    val scrollPosition =
                        ((pagerState.currentPage - Int.MAX_VALUE / 2).floorMod(count) + pagerState.currentPageOffsetFraction)
                            .coerceIn(
                                0f,
                                (count - 1)
                                    .coerceAtLeast(0)
                                    .toFloat()
                            )
                    IntOffset(
                        x = ((spacingPx + indicatorWidthPx) * scrollPosition).toInt(),
                        y = 0
                    )
                }
                .size(width = indicatorWidth, height = indicatorHeight)
                .background(
                    color = activeColor,
                    shape = indicatorShape,
                )
        )
    }
}

fun Int.floorMod(other: Int): Int = when (other) {
    0 -> this
    else -> this - floorDiv(other = other) * other
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ActionsRow(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    infiniteLoop: Boolean = false
) {
    Row(modifier) {
        val scope = rememberCoroutineScope()

        IconButton(
            enabled = infiniteLoop.not() && pagerState.currentPage > 0,
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(0)
                }
            }
        ) {
            Icon(Icons.Default.KeyboardArrowUp, null)
        }

        IconButton(
            enabled = infiniteLoop || pagerState.currentPage > 0,
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                }
            }
        ) {
            Icon(Icons.Default.KeyboardArrowLeft, null)
        }

        IconButton(
            enabled = infiniteLoop || pagerState.currentPage < pagerState.pageCount - 1,
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }
        ) {
            Icon(Icons.Default.KeyboardArrowRight, null)
        }

        IconButton(
            enabled = infiniteLoop.not() && pagerState.currentPage < pagerState.pageCount - 1,
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.pageCount - 1)
                }
            }
        ) {
            Icon(Icons.Default.KeyboardArrowDown, null)
        }
    }
}