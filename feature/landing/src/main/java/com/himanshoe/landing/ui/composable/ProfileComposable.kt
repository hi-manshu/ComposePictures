package com.himanshoe.landing.ui.composable

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonConstants
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.graphics.Color
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
    ScrollableColumn(modifier = Modifier.fullScreen().padding(16.dp)) {
        CoilImage(
            model = "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=80",
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
        Text(
            text = "Himanshu Singh",
            modifier = Modifier.align(Alignment.Start).padding(start = 16.dp, top = 20.dp),
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Text(
            text = "hello2himanshusingh@gmail.com",
            modifier = Modifier.align(Alignment.Start).padding(start = 16.dp, top = 10.dp),
            style = MaterialTheme.typography.body1,
            fontSize = 14.sp
        )
        Divider(
            thickness = 1.dp,
            color = Color.Gray,
            modifier = Modifier.drawOpacity(0.4F).padding(top = 16.dp, bottom = 16.dp)
        )
        Column(
            modifier = Modifier.clip(RoundedCornerShape(10.dp))
                .align(Alignment.End)
                .background(color = "fdecd2".toColor())
                .fillMaxWidth()
        ) {
            Text(
                text = "Complete Profile",
                modifier = Modifier.align(Alignment.Start).padding(start = 16.dp, top = 20.dp),
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Text(
                text = "Click here to edit",
                modifier = Modifier.align(Alignment.Start)
                    .padding(start = 16.dp, top = 10.dp, bottom = 20.dp),
                style = MaterialTheme.typography.body1,
                fontSize = 14.sp
            )
        }
        Button(
            onClick = { },
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
            Text(
                text = "Logout".toUpperCase(Locale.ROOT),
                modifier = Modifier.align(Alignment.CenterVertically).padding(start = 16.dp),
                style = MaterialTheme.typography.body1
            )
        }
    }
}