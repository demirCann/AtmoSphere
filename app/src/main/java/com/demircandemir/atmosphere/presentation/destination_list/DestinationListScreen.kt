package com.demircandemir.atmosphere.presentation.destination_list


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.demircandemir.atmosphere.R


@Composable
fun DestinationListScreen(
    onSearchClicked: () -> Unit,
    onDestinationClicked: (Int) -> Unit,
    destinationViewModel: DestinationViewModel = hiltViewModel()
) {

    val destinations by destinationViewModel.allWeatherData.collectAsState()

    LaunchedEffect(key1 = destinations) {
        destinationViewModel.getDestinations()
    }


    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            DestinationsTopBar(onSearchClicked)
        },
        containerColor = Color(0xFF1D1E33)
    ) { paddingValues ->
        DestinationsContent(
            destinations = destinations,
            paddingValues = paddingValues,
            onDestinationClicked = {
                onDestinationClicked(it)
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DestinationsTopBar(
    onSearchClicked: () -> Unit
) {
    TopAppBar(
        title = { Text(text = "Destinations") },
        actions = {
            IconButton(onClick = { onSearchClicked() }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(id = R.string.search_button)
                )
            }
        },
        colors = TopAppBarColors(
            containerColor = Color(0xFF1D1E33),
            titleContentColor = Color.White,
            actionIconContentColor = Color.White,
            navigationIconContentColor = Color.White,
            scrolledContainerColor = Color(0xFF1D1E33)
        )
    )
}
