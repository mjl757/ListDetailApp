package com.mlievens.listdetailapp.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun ErrorScreen(modifier: Modifier = Modifier, errorMessage: String, onRetryClicked: () -> Unit ) {
    Column(modifier = modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Text(
            text = errorMessage,
            color = Color.Red,
            fontSize = 15.sp
        )
        Button(onClick = onRetryClicked) {
            Text(text = "Retry")
        }

    }

}