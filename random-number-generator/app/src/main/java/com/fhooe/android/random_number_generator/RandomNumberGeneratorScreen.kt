package com.fhooe.android.random_number_generator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RandomNumberGeneratorScreen(
    paddingValues: PaddingValues,
    viewModel: RandomNumberViewModel = viewModel()
) {
    val uiState by viewModel.uiState

    val number = uiState.generatedNumber

    Column(modifier = Modifier.padding(paddingValues)) {
        Text(
            "Generated Number: ${number ?: "-"}"
        )
        TextField(
            value = (uiState.upperLimit ?: "").toString(),
            onValueChange = { newValue ->
                if (newValue.isDigitsOnly()) {
                    viewModel.updateUpperLimit(newValue)
                }
            },
            label = {
                Text("Upper Limit")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Button(
            onClick = { viewModel.generateNumber() },
            enabled = uiState.upperLimit != null
        ) {
            Text("Generate Random Number")
        }
    }
}

@Composable
@Preview
fun RandomNumberGeneratorScreenPreview() {
    RandomNumberGeneratorScreen(PaddingValues.Zero)
}