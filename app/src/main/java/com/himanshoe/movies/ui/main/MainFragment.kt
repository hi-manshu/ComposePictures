package com.himanshoe.movies.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.compose.*
import com.himanshoe.movies.Screen
import com.himanshoe.movies.items
import com.himanshoe.movies.login.composable.LoginUI
import com.himanshoe.movies.ui.movies.MoviesUI

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val navController = rememberNavController()
                Scaffold(
                    topBar = {
                        TopAppBar(backgroundColor = Color.Black,
                            content = {
                                Text(
                                    text = "Movies",
                                    modifier = Modifier.align(Alignment.CenterVertically).padding(start = 16.dp),
                                    style = TextStyle(
                                        color = Color.White,
                                        fontSize = 20.sp,
                                        fontFamily = FontFamily.SansSerif,
                                        textAlign = TextAlign.Justify,
                                    )
                                )
                            })
                    },
                    bottomBar = {
                        BottomNavigation(
                            backgroundColor = Color.Black,
                            contentColor = Color.White,
                            elevation = 16.dp
                        ) {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
                            items.forEach { screen ->
                                BottomNavigationItem(
                                    icon = { Icon(Icons.Filled.Favorite) },
                                    label = { Text(stringResource(screen.resourceId)) },
                                    selected = currentRoute == screen.route,
                                    onClick = {
                                        // This is the equivalent to popUpTo the start destination
                                        navController.popBackStack(
                                            navController.graph.startDestination,
                                            false
                                        )

                                        // This if check gives us a "singleTop" behavior where we do not create a
                                        // second instance of the composable if we are already on that destination
                                        if (currentRoute != screen.route) {
                                            navController.navigate(screen.route)
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) {
                    NavHost(navController, startDestination = Screen.Profile.route) {
                        composable(Screen.Profile.route) { LoginUI() }
                        composable(Screen.Movies.route) { MoviesUI() }
                    }
                }

            }
        }
    }

    @Composable
    private fun Profile() {

    }


}