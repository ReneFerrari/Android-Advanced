package com.fhooe.android.hangry.util

import com.fhooe.android.hangry.api.MealApiService
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        try {
            val response = MealApiService.api.searchMeals("Arrabiata")
            response.meals?.forEach { meal ->
                println("Meal: ${meal.name} — ${meal.category} (${meal.area})")
                println("Instructions: ${meal.instructions}")
                println("Thumbnail: ${meal.thumbnail}")
                println("YouTube: ${meal.youtube}")
                println()
            }
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }
}