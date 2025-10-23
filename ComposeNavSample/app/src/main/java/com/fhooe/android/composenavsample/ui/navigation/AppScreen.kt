package com.fhooe.android.composenavsample.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppScreen : NavKey {
    @Serializable
    data object Home : AppScreen

    @Serializable
    data class Profile(
        val userName: String
    ) : AppScreen

    @Serializable
    data object Settings : AppScreen
}