package com.himanshoe.core.navigator

import androidx.navigation.NavOptionsBuilder
import com.himanshoe.core.R

/**
 * Sets default animation for fragment transition.
 */
fun NavOptionsBuilder.defaultAnimation() {
    anim {
        enter = R.anim.fragment_enter
        exit = R.anim.fragment_exit
        popEnter = R.anim.fragment_pop_enter
        popExit = R.anim.fragment_pop_exit
    }
}

/**
 * Sets fade animation for fragment transition.
 */
fun NavOptionsBuilder.fadeAnimation() {
    anim {
        enter = R.anim.fragment_fade_enter
        exit = R.anim.fragment_fade_exit
        popEnter = R.anim.fragment_fade_enter
        popExit = R.anim.fragment_fade_exit
    }
}
fun NavOptionsBuilder.slideUpAnimation() {
    anim {
        enter = R.anim.fragment_slide_up
        popExit = R.anim.fragment_slide_down
    }
}

