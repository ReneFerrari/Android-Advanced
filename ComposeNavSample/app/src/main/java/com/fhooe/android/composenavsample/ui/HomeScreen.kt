package com.fhooe.android.composenavsample.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fhooe.android.composenavsample.db.User
import com.fhooe.android.composenavsample.db.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun HomeScreen(
    userDao: UserDao,
    onGoToProfile: (userName: String) -> Unit
) {
    var userName by remember { mutableStateOf("") }
    var wasGoToClicked by remember { mutableStateOf(false) }
    LaunchedEffect(wasGoToClicked) {
        if (wasGoToClicked) {
            wasGoToClicked = false
            withContext(Dispatchers.IO) {
                userDao.insertUser(User(name = userName))
            }
            onGoToProfile(userName)
        }

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Screen 1: Home", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(24.dp))

        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text("Enter User Name") },
            singleLine = true

        )
        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                wasGoToClicked = true
            },
            enabled = userName.isNotBlank()
        ) {
            Text("Go to Profile (and save to DB)")
        }
    }
}