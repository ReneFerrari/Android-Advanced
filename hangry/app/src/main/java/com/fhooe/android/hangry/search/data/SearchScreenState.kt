package com.fhooe.android.hangry.search.data

data class SearchScreenState(
    val query: String = "",
    val meals: List<Meal> = emptyList()
)

