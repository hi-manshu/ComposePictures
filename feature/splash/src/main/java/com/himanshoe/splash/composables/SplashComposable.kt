package com.himanshoe.splash.composables

import androidx.compose.animation.core.TransitionState
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.animation.transition
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.himanshoe.core.extension.fullScreen

@Composable
fun SplashUI() {
    val colorState = remember { mutableStateOf(ColorState.BLACK) }

    val transitionDefinition = transitionDefinition<ColorState> {
        state(ColorState.BLACK) { this[color] = Color.Black }
        state(ColorState.WHITE) { this[color] = Color.White }
        transition(fromState = ColorState.BLACK, toState = ColorState.WHITE) {
            color using tween(
                durationMillis = 1500
            )
        }
    }

    val state = transition(
        definition = transitionDefinition,
        initState = colorState.value,
        toState = ColorState.WHITE
    )
    splashBackground(colorState, state = state)

}


@Composable
fun splashBackground(colorState: MutableState<ColorState>, state: TransitionState) {
    Box(modifier = Modifier.fullScreen(state[color]))
}
