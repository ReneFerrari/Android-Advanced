package com.fhooe.android.hangry.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MealResponse(
    val meals: List<Meal>?
)

@JsonClass(generateAdapter = true)
data class Meal(
    @Json(name = "idMeal") val id: String?,
    @Json(name = "strMeal") val name: String?,
    @Json(name = "strCategory") val category: String?,
    @Json(name = "strArea") val area: String?,
    @Json(name = "strInstructions") val instructions: String?,
    @Json(name = "strMealThumb") val thumbnail: String?,
    @Json(name = "strYoutube") val youtube: String?
)