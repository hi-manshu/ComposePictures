package com.himanshoe.login.ui.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Text
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.himanshoe.core.extension.fullScreen
import com.himanshoe.core.util.validator.isEmail
import com.himanshoe.login.ui.LoginViewModel
import java.util.*

@Composable
fun LoginUI(viewModel: LoginViewModel) {
    Scaffold(
        modifier = Modifier.fullScreen().padding(16.dp),
        bodyContent = {

            val emailState = remember { mutableStateOf(TextFieldValue()) }
            val passwordState = remember { mutableStateOf(TextFieldValue()) }

            Column {
                Text(
                    text = "Skip",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.body1
                )
                addSpace()
                Text(
                    text = "Welcome to,\nPhoto Collector",
                    style = MaterialTheme.typography.h4
                )
                addSpace(32.dp)

                inputField(KeyboardType.Email, Icons.Filled.Email, emailState, LoginField.EMAIL)

                if (!emailState.value.isEmail() && emailState.value.text.isNotEmpty()) {
                    Text(
                        emailState.value.text + " is not a valid email",
                        style = TextStyle(color = Color.Red)
                    )
                }

                addSpace()

                inputField(
                    KeyboardType.Password,
                    (Icons.Filled.RemoveRedEye),
                    passwordState,
                    LoginField.PASSWORD
                )

                addSpace(32.dp)

                buttonLogin("Login") {
                    viewModel.doLoginViaEmail(emailState.value.text, passwordState.value.text)
                }

                addSpace(8.dp)

                Text(
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
            Text(
                text = "Made with ❤ in India",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6.merge(
                    TextStyle(
                        fontWeight = FontWeight.Bold
                    )
                )
            )
        })


}

@Composable
private fun inputField(
    type: KeyboardType,
    icon: VectorAsset,
    state: MutableState<TextFieldValue>,
    field: LoginField
) {
    TextField(
        keyboardType = type,
        activeColor = Color.Gray,
        modifier = Modifier.fillMaxWidth().border(
            BorderStroke(0.5.dp, Color.Gray),
            shape = RoundedCornerShape(percent = 0)
        ),
        value = state.value,
        trailingIcon = { Icon(icon) },
        visualTransformation = if (field == LoginField.PASSWORD) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        onValueChange = { state.value = it }
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
        Text(
            text = text.toUpperCase(Locale.ROOT),
            modifier = Modifier.align(Alignment.CenterVertically).padding(start = 16.dp),
            style = MaterialTheme.typography.body1
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