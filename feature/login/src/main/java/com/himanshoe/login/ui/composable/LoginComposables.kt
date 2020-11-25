package com.himanshoe.login.ui.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.himanshoe.core.extension.fullScreen
import com.himanshoe.core.extension.toColor
import com.himanshoe.core.util.validator.isEmail
import com.himanshoe.login.ui.LoginViewModel
import java.util.*

@Composable
fun LoginUI(viewModel: LoginViewModel) {
    Scaffold(

        bodyContent = {

            val emailState = remember { mutableStateOf(TextFieldValue()) }
            val passwordState = remember { mutableStateOf(TextFieldValue()) }

            Column(modifier = Modifier.fullScreen().padding(16.dp)) {

                addSpace()

                BasicText(
                    text = "Welcome to,\nPhoto Collector",
                    style = MaterialTheme.typography.h4
                )
                addSpace(32.dp)

                inputField(Icons.Filled.Email, emailState, LoginField.EMAIL)

                if (!emailState.value.isEmail() && emailState.value.text.isNotEmpty()) {
                    BasicText(
                        emailState.value.text + " is not a valid email",
                        style = TextStyle(color = Color.Red)
                    )
                }

                addSpace()

                inputField(
                    (Icons.Filled.RemoveRedEye),
                    passwordState,
                    LoginField.PASSWORD
                )

                addSpace(32.dp)

                buttonLogin("Login") {
                    viewModel.doLoginViaEmail(emailState.value.text, passwordState.value.text)
                }

                addSpace(8.dp)

                BasicText(
                    text = "or",
                    modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        letterSpacing = TextUnit.Sp(1.5),
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                    )
                )
                addSpace(8.dp)

                buttonLogin("Login with Google", Color.DarkGray) {
                    viewModel.doLoginViewGoogle()
                }

            }
        },
        bottomBar = {

            BasicText(
                text = "Made with ❤ in India",
                modifier = Modifier.fillMaxWidth().height(50.dp)
                    .clip(RoundedCornerShape(topLeft = 40.dp, topRight = 40.dp))
                    .background("4D73F6".toColor()).padding(top = 12.dp),
                style = MaterialTheme.typography.body1.merge(
                    TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
            )
        })
}

@Composable
private fun inputField(
    icon: VectorAsset,
    state: MutableState<TextFieldValue>,
    field: LoginField
) {
    TextField(
        value = state.value,
        activeColor = Color.Gray,
        modifier = Modifier.fillMaxWidth().border(
            BorderStroke(0.5.dp, Color.Gray),
            shape = RoundedCornerShape(percent = 0)
        ),
        trailingIcon = {
            Icon(asset = icon)
        },
        visualTransformation = if (field == LoginField.PASSWORD) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        onValueChange = { value -> state.value = value }
    )
}

@Composable
private fun buttonLogin(text: String, color: Color = Color.Black, onClick: () -> Unit) {
    Button(
        onClick = { onClick.invoke() },
        elevation = ButtonConstants.defaultElevation(
            defaultElevation = 8.dp,
            pressedElevation = 16.dp
        ),
        colors = ButtonConstants.defaultButtonColors(
            backgroundColor = color,
            contentColor = Color.White
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        BasicText(
            text = text.toUpperCase(Locale.ROOT),
            modifier = Modifier.align(Alignment.CenterVertically).padding(start = 16.dp),
            style = TextStyle(color = Color.White),
        )
    }
}

enum class LoginField {
    EMAIL,
    PASSWORD
}

@Composable
fun addSpace(spaceHeight: Dp = 16.dp) {
    Spacer(modifier = Modifier.height(spaceHeight))
}