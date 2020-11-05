package com.himanshoe.landing.ui.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.gesture.doubleTapGestureFilter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.himanshoe.core.common.CoilImage
import com.himanshoe.core.extension.fullScreen
import com.himanshoe.landing.data.response.PhotoResponse
import com.himanshoe.landing.ui.LandingViewModel


@Composable
fun PhotoUI(viewModel: LandingViewModel) {
    val photosState: State<List<PhotoResponse>?> = viewModel.photos.observeAsState()
    val photosList: List<PhotoResponse> = photosState.value ?: emptyList()
    val isLoading = remember { mutableStateOf(true) }
    if (isLoading.value) {
        showProgress()
    }
    LazyColumnForIndexed(photosList) { index: Int, item: PhotoResponse ->
        CoilImage(
            model = item.downloadUrl,
            modifier = Modifier.padding(2.dp).doubleTapGestureFilter {
                viewModel.openImage(item.downloadUrl)
            })


        if (photosList.isNotEmpty() && photosList.count().minus(5) == index) {
            onActive {
                viewModel.loadMorePhotos()
            }
        }
        isLoading.value = false
    }
    openImage(viewModel)

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

