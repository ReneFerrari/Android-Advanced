package com.fhooe.android.composenavsample.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fhooe.android.composenavsample.db.UserDao

@Composable
fun ProfileScreen(
    userName: String,
    userDao: UserDao,
    onGoToSettings: () -> Unit,
    onGoBack: () -> Unit
) {
    val user by userDao.getUserByName(userName).collectAsStateWithLifecycle(initialValue = null)

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Screen 2: Profile", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))

        Text(
            text = "Viewing profile for ID: ${user?.id}",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(Modifier.height(8.dp))

        Text(
            text = "Name from DB: ${user?.name ?: "Loading..."}",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(Modifier.height(24.dp))
        Button(onClick = onGoToSettings) {
            Text("Go to Settings")
        }
        Spacer(Modifier.height(8.dp))
        Button(onClick = onGoBack) {
            Text("Go Back")
        }
    }
}