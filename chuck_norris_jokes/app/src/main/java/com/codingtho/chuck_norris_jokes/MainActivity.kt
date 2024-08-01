package com.codingtho.chuck_norris_jokes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.codingtho.chuck_norris_jokes.ui.screen.view.MainScreen
import com.codingtho.chuck_norris_jokes.ui.theme.Chuck_norris_jokesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Chuck_norris_jokesTheme {
                MainScreen()
            }
        }
    }
}
