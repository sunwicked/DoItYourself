package com.weather.compose.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.weather.compose.model.Weather
import com.weather.compose.ui.theme.WeatherComposeTheme

@Composable
fun WeatherRow(item: Weather, modifier: Modifier = Modifier) {
    Row(modifier = modifier
        .height(64.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center) {
        Text(
            text = item.name,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 8.dp),
            style =  MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherRowPreview() {
    WeatherComposeTheme {
        WeatherRow(Weather("Android"))
    }
}