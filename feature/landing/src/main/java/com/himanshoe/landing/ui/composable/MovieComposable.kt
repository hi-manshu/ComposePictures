package com.himanshoe.landing.ui.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.onActive
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.himanshoe.landing.data.response.PhotoResponse
import com.himanshoe.landing.ui.LandingViewModel

@Composable
fun PhotoUI(viewModel: LandingViewModel) {
    val photosState: State<List<PhotoResponse>?> = viewModel.photos.observeAsState()
    val photosList: List<PhotoResponse> = photosState.value ?: emptyList()

    val list: List<List<PhotoResponse>> = photosList.chunked(2)
    LazyColumnForIndexed(photosList) { index: Int, item: PhotoResponse ->
        CoilImage(model = item.downloadUrl, modifier = Modifier.padding(2.dp))

        if (photosList.isNotEmpty() && photosList.count().minus(5) == index) {
            onActive {
                viewModel.loadMorePhotos()
            }
        }
    }
}

