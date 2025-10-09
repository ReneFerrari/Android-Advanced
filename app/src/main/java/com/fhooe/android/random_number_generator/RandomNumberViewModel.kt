package com.fhooe.android.random_number_generator

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class RandomNumberViewModel: ViewModel() {
    val uiState: MutableState<RandomNumberState> = mutableStateOf(RandomNumberState())

    fun generateNumber() {
        val currentLimit = uiState.value.upperLimit

        if (currentLimit == null) {
            return
        }
        uiState.value = uiState.value.copy(
            generatedNumber = Random.nextInt(currentLimit + 1)
        )
    }

    fun updateUpperLimit(newValue: String) {
        uiState.value = uiState.value.copy(
            upperLimit = newValue.toIntOrNull()
        )
    }
}
