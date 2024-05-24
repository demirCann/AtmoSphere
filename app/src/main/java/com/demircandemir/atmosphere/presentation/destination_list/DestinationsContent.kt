package com.demircandemir.atmosphere.presentation.destination_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.demircandemir.atmosphere.R
import com.demircandemir.atmosphere.domain.model.WeatherDataEntity



@Composable
fun DestinationsContent(
    destinations: List<WeatherDataEntity>,
    paddingValues: PaddingValues,
    onDestinationClicked: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        items(destinations.size) { index ->
            DestinationCard(
                temperature = destinations[index].temperature.toInt().toString(),
                highTemp = destinations[index].daily[0].temp.max.toInt().toString(),
                lowTemp = destinations[index].daily[0].temp.min.toInt().toString(),
                cityName = destinations[index].cityName,
                weatherDescription = destinations[index].daily[0].weather[0].main,
                weatherIconRes = destinations[index].daily[0].weather[0].icon,
                onDestinationClicked = { onDestinationClicked(index) }
            )
        }
    }

}


@Composable
fun DestinationCard(
    temperature: String,
    highTemp: String,
    lowTemp: String,
    cityName: String,
    weatherDescription: String,
    weatherIconRes: String,
    onDestinationClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF87CEFA), Color(0xFF4682B4))
                )
            )
            .clickable { onDestinationClicked() },
        colors = CardColors(
            containerColor = Color(0xFF87CEFA),
            contentColor = Color.White,
            disabledContentColor = Color.White,
            disabledContainerColor = Color(0xFF87CEFA),
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = temperature,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "H:$highTemp L:$lowTemp",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = cityName.split("/").last(),
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = weatherDescription,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    )
                }

                AsyncImage(
                    model = "https://openweathermap.org/img/wn/${weatherIconRes}.png",
                    modifier = Modifier.size(96.dp),
                    contentDescription = stringResource(R.string.weather_icon),
                )

            }
        }
    }
}
