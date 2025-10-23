package com.fhooe.android.composenavsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.room.Room
import com.fhooe.android.composenavsample.db.User
import com.fhooe.android.composenavsample.db.UserDatabase
import com.fhooe.android.composenavsample.ui.HomeScreen
import com.fhooe.android.composenavsample.ui.ProfileScreen
import com.fhooe.android.composenavsample.ui.SettingsScreen
import com.fhooe.android.composenavsample.ui.navigation.AppScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java, "user-database"
        ).build()
        val userDao = db.userDao()

        setContent {
            val backStack = rememberNavBackStack(AppScreen.Home)

            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    NavDisplay(
                        backStack = backStack,
                        onBack = { backStack.removeLastOrNull() },
                        entryProvider = { key ->
                            NavEntry(key) {
                                when (key) {
                                    is AppScreen.Home -> HomeScreen(
                                        userDao = userDao,
                                        onGoToProfile = { userName ->
                                            backStack.add(AppScreen.Profile(userName))
                                        }
                                    )

                                    is AppScreen.Profile -> {
                                        ProfileScreen(
                                            userName = key.userName,
                                            userDao = userDao, // 3. Pass DAO for reading
                                            onGoToSettings = {
                                                backStack.add(AppScreen.Settings)
                                            },
                                            onGoBack = {
                                                backStack.removeLastOrNull()
                                            }
                                        )
                                    }

                                    is AppScreen.Settings -> SettingsScreen(
                                        onGoBack = {
                                            backStack.removeLastOrNull()
                                        }
                                    )
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}