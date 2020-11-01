package com.himanshoe.movies.login.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.outlined.Cancel
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.himanshoe.movies.util.isEmail
import java.util.*

@Composable
fun LoginUI() {
    Scaffold(modifier = Modifier.padding(16.dp), bodyContent = {

        val emailState = remember { mutableStateOf(TextFieldValue()) }
        val passwordState = remember { mutableStateOf(TextFieldValue()) }

        Column(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
            Image(asset = Icons.Outlined.Cancel, modifier = Modifier.height(32.dp).width(32.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Welcome to,\nPhoto Collector",
                style = TextStyle(
                    fontSize = 32.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                )
            )
            Spacer(modifier = Modifier.height(32.dp))

            inputField(KeyboardType.Email, Icons.Filled.Email, emailState)

            if(!emailState.value.isEmail() && emailState.value.text.isNotEmpty()){
                Text(emailState.value.text +" is not a valid email",style = TextStyle(color = Color.Red))
            }

            Spacer(modifier = Modifier.height(32.dp))

            inputField(KeyboardType.Password, (Icons.Filled.RemoveRedEye), passwordState)

            Spacer(modifier = Modifier.height(32.dp))

            buttonLogin("Login") {

            }


            Spacer(modifier = Modifier.height(8.dp))
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
            Spacer(modifier = Modifier.height(8.dp))

            buttonLogin("Login with Google") {

            }

        }
    }, bottomBar = {

    })


}

@Composable
private fun inputField(
    type: KeyboardType,
    icon: VectorAsset,
    state: MutableState<TextFieldValue>
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
        onValueChange = { state.value = it }
    )
}

@Composable
private fun buttonLogin(text:String, onClick: () -> Unit) {
    Button(
        onClick = { onClick.invoke() },
        elevation = ButtonConstants.defaultElevation(
            defaultElevation = 8.dp,
            pressedElevation = 16.dp
        ),
        colors = ButtonConstants.defaultButtonColors(
            backgroundColor = Color.Black,
            contentColor = Color.White
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = text.toUpperCase(Locale.ROOT),
            modifier = Modifier.align(Alignment.CenterVertically).padding(start = 16.dp),
            style = TextStyle(
                letterSpacing = TextUnit.Sp(1.5),
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
            )
        )
    }
}