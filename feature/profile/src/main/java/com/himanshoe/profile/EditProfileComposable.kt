package com.himanshoe.profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.himanshoe.core.common.CoilImage
import com.himanshoe.core.extension.toColor
import com.himanshoe.core.model.User

@Composable
fun EditUI(source: String, viewModel: EditProfileViewModel) {
    val user: User? = viewModel.user.observeAsState().value
    if (user != null) {

        val nameState = remember { mutableStateOf(TextFieldValue(text = user.name)) }
        val emailState = remember { mutableStateOf(TextFieldValue(text = user.email)) }
        val descriptionState = remember { mutableStateOf(TextFieldValue(text = user.description)) }

        Scaffold(topBar = {
            TopAppBar(modifier = Modifier.fillMaxWidth(), backgroundColor = Color.Black) {
                BasicText(
                    text = "Edit Profile",
                    style = TextStyle(color = Color.White, fontSize = 16.sp),
                    modifier = Modifier.align(Alignment.CenterVertically).padding(start = 16.dp)
                )
            }
        }, bodyContent = {
            ScrollableColumn(
                modifier = Modifier.fillMaxWidth()
                    .fillMaxHeight()
                    .background(color = Color.White)
                    .padding(16.dp)
            ) {
                CoilImage(
                    model = user.displayUrl,
                    modifier = Modifier.preferredSize(100.dp)
                        .fillMaxWidth()
                        .background(color = "fdecd2".toColor(), shape = CircleShape)
                        .align(Alignment.Start).clip(CircleShape)
                ) {}
                BasicText(
                    text = "Hey,",
                    style = MaterialTheme.typography.h6
                )

                Spacer(modifier = Modifier.height(8.dp))
                inputField(state = nameState)
                Spacer(modifier = Modifier.height(16.dp))

                Divider(
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                    color = Color.Gray,
                    thickness = 1.dp
                )

                BasicText(
                    text = "Email",
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.height(8.dp))

                textField(emailState.value.text)
                Spacer(modifier = Modifier.height(8.dp))
                Divider(
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                    color = Color.Gray,
                    thickness = 1.dp
                )
                BasicText(
                    text = "Description",
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.height(8.dp))
                inputDescriptionField(state = descriptionState)

            }

        }, bottomBar = {
            bottomSaveButton {
                viewModel.saveUser(
                    nameState.value.text,
                    emailState.value.text,
                    descriptionState.value.text
                )
            }
        })
    }

}

@Composable
fun inputField(state: MutableState<TextFieldValue>) {
    TextField(
        activeColor = Color.Gray,
        modifier = Modifier.fillMaxWidth().border(
            BorderStroke(0.5.dp, Color.Gray),
            shape = RoundedCornerShape(percent = 0)
        ),
        value = state.value,
        onValueChange = { value -> state.value = value }
    )

}

@Composable
fun inputDescriptionField(state: MutableState<TextFieldValue>) {
    TextField(
        activeColor = Color.Gray,
        modifier = Modifier.fillMaxWidth().height(150.dp).border(
            BorderStroke(0.5.dp, Color.Gray),
            shape = RoundedCornerShape(percent = 0)
        ),
        value = state.value,
        onValueChange = { value -> state.value = value }
    )

}

@Composable
fun textField(value: String) {
    BasicText(
        modifier = Modifier.fillMaxWidth(),
        text = value,
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
    )

}

@Composable
fun bottomSaveButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth().padding(end = 12.dp, bottom = 12.dp),
        alignment = Alignment.BottomEnd
    ) {
        ExtendedFloatingActionButton(
            text = {
                Row {
                    Image(Icons.Filled.Save)
                    BasicText(text = "Save", modifier = Modifier.padding(start = 12.dp))
                }
            },
            onClick = { onClick() }, backgroundColor = "fdecd2".toColor(),
        )
    }
}
