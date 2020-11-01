package com.himanshoe.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.himanshoe.movies.login.ui.LoginFragment
import com.himanshoe.movies.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, LoginFragment.newInstance())
                    .commitNow()
        }
    }
}