package com.demircandemir.atmosphere.presentation.add_city

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.demircandemir.atmosphere.R

@Composable
fun SearchTopAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onClosedClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {
    SearchWidget(
        text = text,
        onTextChange = { onTextChange(it) },
        onClosedClicked = { onClosedClicked()},
        onSearchClicked = { onSearchClicked(text) }
    )
}


@Composable
fun SearchWidget(
    text: String,
    onTextChange: (String) -> Unit,
    onClosedClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = { onTextChange(it) },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(0.74f),
                    text = "Search",
                    color = MaterialTheme.colorScheme.onSurface,
                )
            },
            textStyle = TextStyle(
                color = Color.Black
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier
                        .alpha(0.74f),
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(R.string.search_button),
                        tint = Color.Black
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    modifier = Modifier
                        .alpha(0.74f),
                    onClick = {
                        if (text.isNotEmpty()){
                            onTextChange("")
                        }
                        else {
                            onClosedClicked()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(R.string.close_button),
                        tint = Color.Black
                    )
                }
            },

            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),

            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),

            colors = TextFieldDefaults.colors()

        )
    }
}

@Composable
@Preview
fun SearchWidgetPreview() {
    SearchWidget(
        text = "",
        onTextChange = {},
        onClosedClicked = {},
        onSearchClicked = {}
    )
}