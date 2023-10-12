package com.weather.compose.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import com.weather.compose.model.Weather
import com.weather.compose.ui.screen.WeatherScreen
import com.weather.compose.ui.theme.WeatherComposeTheme

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WeatherScreen(
                        listOf(
                            Weather("Android"),
                            Weather("Compose"),
                            Weather("Coroutine")
                        )
                    )
                }
            }
        }
    }
}

