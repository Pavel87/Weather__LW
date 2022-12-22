package com.pacmac.weather.WeatherSearchUI

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.pacmac.weather.Constants
import com.pacmac.weather.R
import com.pacmac.weather.model.WeatherModel
import com.pacmac.weather.ui.theme.ComposeWeatherTheme
import com.pacmac.weather.utils.Utils
import java.util.*

/**
 * @Author: Pavel Machala
 * @Date: 2022-12-22
 */
@Composable
fun WeatherItem(weatherModel: WeatherModel) {
    Surface(
        color = MaterialTheme.colors.surface,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(2f)) {
                Text(text = weatherModel.city, fontWeight = FontWeight.Bold)
                Image(
                    painter = rememberAsyncImagePainter(
                        String.format(
                            Constants.WEATHER_ICON_URL,
                            weatherModel.icon
                        ),
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .padding(top = 4.dp)
                )
                Text(text = weatherModel.weather, modifier = Modifier.padding(top = 4.dp))
            }

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = Utils.formatTime(weatherModel.time))
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(text = "${weatherModel.temperature}")
                    Text(text = stringResource(id = R.string.degrees))
                }

            }
        }
    }
}

@Preview
@Composable
fun previewWeatherItem() {
    ComposeWeatherTheme {
        WeatherItem(
            weatherModel = WeatherModel(
                "Victoria",
                Date().time,
                -5.0f,
                "Clear",
                "03d",
            )
        )
    }
}
