package com.himanshoe.landing.ui.composable

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Button
import androidx.compose.material.ButtonConstants
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.himanshoe.core.common.CoilImage
import com.himanshoe.core.extension.fullScreen
import com.himanshoe.core.extension.toColor
import com.himanshoe.landing.ui.LandingViewModel
import java.util.*

@Composable
fun ProfileUI(viewModel: LandingViewModel) {
    val user = viewModel.user.observeAsState().value
    val descriptionState = remember { mutableStateOf(user?.description) }
    ScrollableColumn(modifier = Modifier.fullScreen().padding(16.dp)) {
        CoilImage(
            model = user?.displayUrl ?: "",
            modifier = Modifier.preferredSize(200.dp)
                .fillMaxWidth()
                .background(color = "fdecd2".toColor(), shape = CircleShape)
                .align(Alignment.CenterHorizontally).clip(CircleShape)
        ) {}
        Divider(
            thickness = 1.dp,
            color = Color.Gray,
            modifier = Modifier.drawOpacity(0.4F).padding(top = 20.dp)
        )
        BasicText(
            text = user?.name?.capitalize(Locale.ROOT) ?: "",
            modifier = Modifier.align(Alignment.Start).padding(start = 16.dp, top = 20.dp),
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp),
        )
        BasicText(
            text = user?.email ?: "",
            modifier = Modifier.align(Alignment.Start).padding(start = 16.dp, top = 10.dp),
        )
        Divider(
            thickness = 1.dp,
            color = Color.Gray,
            modifier = Modifier.drawOpacity(0.4F).padding(top = 16.dp, bottom = 16.dp)
        )
        setDescription(descriptionState) {
            viewModel.openEditScreen(user?.source)
        }

        Button(
            onClick = {
                viewModel.logout()
            },
            elevation = ButtonConstants.defaultElevation(
                defaultElevation = 8.dp,
                pressedElevation = 16.dp
            ),
            colors = ButtonConstants.defaultButtonColors(
                backgroundColor = Color.Black,
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            BasicText(
                style = TextStyle(color = Color.White),
                text = "Logout".toUpperCase(Locale.ROOT),
                modifier = Modifier.align(Alignment.CenterVertically),
            )
        }
    }
}

@Composable
fun setDescription(descriptionState: State<String?>, onClick: () -> Unit) {
    val value: String? = descriptionState.value ?: ""
    if (value?.isEmpty()!!) {
        editCard(title = "Complete Profile", onClick = onClick)
    } else {
        BasicText(
            text = """" $value """",
            modifier = Modifier.clip(RoundedCornerShape(10.dp))
                .background(color = "e2e2e2".toColor())
                .padding(16.dp)
                .fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        editCard(title = "Edit Profile", onClick = onClick)


    }
}

@Composable
private fun editCard(title: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier.clip(RoundedCornerShape(10.dp))
            .background(color = "fdecd2".toColor())
            .fillMaxWidth()
            .clickable(onClick = {
                onClick()
            })
    ) {
        BasicText(
            text = title,
            modifier = Modifier.align(Alignment.Start).padding(start = 16.dp, top = 20.dp),
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp),
        )
        BasicText(
            text = "Click here to edit",
            modifier = Modifier.align(Alignment.Start)
                .padding(start = 16.dp, top = 10.dp, bottom = 20.dp),
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp),
        )
    }
}
