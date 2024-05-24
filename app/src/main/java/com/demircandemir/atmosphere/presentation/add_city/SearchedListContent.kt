package com.demircandemir.atmosphere.presentation.add_city

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.demircandemir.atmosphere.data.local.CityEntity

@Composable
fun SearchedListContent(
    searchedCities: List<CityEntity?>,
    paddingValues: PaddingValues,
    onFetchDataFromRemote: (lat: Double, lon: Double) -> Unit,
    onClickedCity: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
    ) {
        items(searchedCities.size) { index ->
            searchedCities[index]?.let { city ->
                CityItem(
                    city = city,
                   onFetchDataFromRemote = { lat, lon ->
                        onFetchDataFromRemote(lat, lon)
                    },
                    onClickedCity = { onClickedCity() }
                )
            }
        }
    }
}

@Composable
fun CityItem(
    city: CityEntity,
    onFetchDataFromRemote: (lat: Double, lon: Double) -> Unit,
    onClickedCity: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onFetchDataFromRemote(city.latitude, city.longitude)
                onClickedCity()
            }
    ) {
        Text(text = city.name)
    }
}
