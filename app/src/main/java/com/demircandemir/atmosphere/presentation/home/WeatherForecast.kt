package com.demircandemir.atmosphere.presentation.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.demircandemir.atmosphere.R
import com.demircandemir.atmosphere.domain.model.Daily
import com.demircandemir.atmosphere.domain.model.Hourly
import com.demircandemir.atmosphere.domain.model.WeatherDataEntity


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPage(
    weatherDataEntities: List<WeatherDataEntity>,
    selectedCityIndex: Int,
    onNavigateCityListScreen: () -> Unit
) {

    val pagerState = rememberPagerState(initialPage = selectedCityIndex, pageCount = { weatherDataEntities.size })





    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFF87CEFA), Color(0xFF4682B4))))
    ) {


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = { onNavigateCityListScreen() },
                modifier = Modifier.size(64.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = stringResource(R.string.cities_button),
                    modifier = Modifier
                        .padding(16.dp, 16.dp)
                        .size(48.dp),
                    tint = Color.White
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->

            val weatherData = weatherDataEntities[page]
            WeatherForecastScreen(weatherData)
        }



        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) Color.White else Color(0xFF4682B4)
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .size(16.dp)
                        .background(color, shape = CircleShape)
                        .border(1.dp, Color.White, CircleShape)
                )
            }
        }

    }


}




@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherForecastScreen(
    weatherData: WeatherDataEntity
) {

    val state = rememberScrollState()


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(state)
        ) {

            Spacer(modifier = Modifier.height(16.dp))
            CurrentWeatherSection(weatherData.cityName, weatherData.temperature.toInt().toString(), weatherData.weatherMain)
            Spacer(modifier = Modifier.height(48.dp))
            HourlyForecastSection(weatherData.hourly)
            Spacer(modifier = Modifier.height(24.dp))
            NextForecastSection(weatherData.daily)
        }

}

@Composable
fun CurrentWeatherSection(
    cityName: String,
    temperature: String,
    weather: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(cityName.split("/").last(), fontSize = 24.sp, color = Color.White)
        Text(text = "$temperature\u00B0C", fontSize = 48.sp, color = Color.White)
        Text(weather, fontSize = 20.sp, color = Color.White)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HourlyForecastSection(
    hourlyList: List<Hourly>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardColors(containerColor = Color(0x80FFFFFF), contentColor = Color.Black, disabledContentColor = Color(0x80FFFFFF), disabledContainerColor = Color(0x80FFFFFF))
    ) {
        LazyRow(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
        ) {

            items(hourlyList.size) {
                HourlyForecastItem(
                    hourly = hourlyList[it]
                )
            }
        }
    }



}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HourlyForecastItem(
    hourly: Hourly
) {
    Column(
        modifier = Modifier
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "${hourly.temp.toInt()}\u00B0C", color = Color.White)
        // Example icons can be replaced with actual weather icons
        AsyncImage(
            model = "https://openweathermap.org/img/wn/${hourly.weather[0].icon}.png",
            modifier = Modifier.size(56.dp),
            contentDescription = stringResource(R.string.weather_icon),
        )

        Text("${hourly.hour}.00", color = Color.White)
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NextForecastSection(
    dailyList: List<Daily>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardColors(containerColor = Color(0x80FFFFFF), contentColor = Color.Black, disabledContentColor = Color(0x80FFFFFF), disabledContainerColor = Color(0x80FFFFFF))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Next Forecast", fontSize = 20.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(8.dp))
            dailyList.forEach { daily ->
                ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                    val (dayText, iconImage, tempText) = createRefs()

                    Text(
                        text = daily.dayOfWeek,
                        color = Color.Black,
                        modifier = Modifier.constrainAs(dayText) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }
                    )

                    AsyncImage(
                        model = "https://openweathermap.org/img/wn/${daily.weather[0].icon}.png",
                        modifier = Modifier
                            .size(48.dp)
                            .constrainAs(iconImage) {
                                end.linkTo(tempText.start, margin = 96.dp)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            },
                        contentDescription = stringResource(R.string.weather_icon),
                    )

                    Text(
                        text = "${daily.temp.day.toInt()}°C",
                        color = Color.Black,
                        modifier = Modifier.constrainAs(tempText) {
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}
