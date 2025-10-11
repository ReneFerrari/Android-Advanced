package com.fhooe.android.hangry.api.repository

import com.fhooe.android.hangry.api.MealApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchRepository(
    private val api: MealApi
) {
    suspend fun searchMeals(query: String) = withContext(Dispatchers.IO) {
        runCatching {
            api.searchMeals(query).meals ?: emptyList()
        }
    }
}