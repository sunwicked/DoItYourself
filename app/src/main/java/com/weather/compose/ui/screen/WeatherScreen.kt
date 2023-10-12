package com.weather.compose.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.weather.compose.model.Weather
import com.weather.compose.ui.theme.WeatherComposeTheme

@Composable
fun WeatherScreen(items: List<Weather>, modifier: Modifier = Modifier) {
    Column {
        items.forEach { weather ->
            WeatherRow(weather)
            Divider(color = Color.Black)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherPreview() {
    WeatherComposeTheme {
        WeatherScreen(listOf(Weather("Android"),Weather("Weather")))
    }
}