package com.himanshoe.landing.ui.composable

import android.util.Log
import androidx.compose.foundation.Text
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.onActive
import com.himanshoe.landing.data.response.PhotoResponse
import com.himanshoe.landing.ui.LandingViewModel

@Composable
fun PhotoUI(viewModel: LandingViewModel) {
    val photosState: State<List<PhotoResponse>?> = viewModel.photos.observeAsState()
    val photosList: List<PhotoResponse> = photosState.value ?: emptyList()

    LazyColumnForIndexed(photosList) { index: Int, item: PhotoResponse ->
        Text(text = item.downloadUrl)

        if (photosList.isNotEmpty() && photosList.count().minus(5) == index) {
            onActive {
                viewModel.loadMorePhotos()
            }
        }
    }
}