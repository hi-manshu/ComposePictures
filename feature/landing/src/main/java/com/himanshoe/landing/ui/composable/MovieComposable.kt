package com.himanshoe.landing.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.himanshoe.core.common.CoilImage
import com.himanshoe.core.extension.fullScreen
import com.himanshoe.core.extension.toColor
import com.himanshoe.landing.ui.LandingViewModel


@Composable
fun PhotoUI(viewModel: LandingViewModel) {

    val photos = viewModel.getPhotoPagination().collectAsLazyPagingItems()

    LazyColumn(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {

        items(photos) { photo ->
            Box {
                photo?.downloadUrl?.let { loadImage(it) }
                BasicText(
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    text = "Clicked by: " + photo?.author.toString(),
                    modifier = Modifier.padding(top = 8.dp, start = 4.dp)
                        .background(color = "#80FFFFFF".toColor())
                        .padding(4.dp)
                )
            }

        }
        photos.apply {
            when {
                loadState.refresh is LoadState.Loading -> item {
                    showProgress()
                }
                loadState.append is LoadState.Loading -> {
                    item { showProgress() }
                }
            }
        }
    }

    openImage(viewModel)

}

@Composable
fun loadImage(url: String) {
    CoilImage(
        model = url,
        modifier = Modifier.fillMaxWidth()
            .border(
                width = 4.dp,
                color = Color.White,
                shape = RoundedCornerShape(0.dp)
            )
    )
}

@Composable
fun openImage(viewModel: LandingViewModel) {
    val url = viewModel.photo.observeAsState("")
    if (url.value.isNotEmpty()) {
        Dialog(
            content = {
                CoilImage(
                    model = url.value,
                    modifier = Modifier.fillMaxWidth()
                        .border(width = 5.dp, color = Color.White, shape = RoundedCornerShape(0.dp))
                )
            },
            onDismissRequest = { viewModel.openImage("") }
        )
    }
}

@Composable
fun showProgress() {
    Box(modifier = Modifier.fullScreen(backgroundColor = Color.White)) {
        CircularProgressIndicator(
            color = Color.Black,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
