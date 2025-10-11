package com.fhooe.android.hangry.api

import com.fhooe.android.hangry.api.response.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("search.php")
    suspend fun searchMeals(
        @Query("s") query: String
    ): MealResponse
}