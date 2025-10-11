package com.fhooe.android.hangry.search.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.fhooe.android.hangry.api.MealApiService
import com.fhooe.android.hangry.api.repository.SearchRepository
import com.fhooe.android.hangry.search.data.Meal
import com.fhooe.android.hangry.search.data.SearchViewModel

@Composable
fun SearchScreenHost() {
    val viewModel by remember {
        mutableStateOf(
            SearchViewModel(
                searchRepository = SearchRepository(
                    api = MealApiService.api
                )
            )
        )
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SearchScreen(
        meals = uiState.meals,
        query = uiState.query,
        onSearchQueryChanged = { query ->
            viewModel.findMeals(query)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    meals: List<Meal> = emptyList(),
    query: String,
    onSearchQueryChanged: (String) -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Meal Search") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = { newQuery ->
                    onSearchQueryChanged(newQuery)
                },
                modifier = Modifier
                    .fillMaxWidth(),
                label = { Text("Search meals") },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            when {
                meals.isEmpty() && query.length > 2 -> {
                    Text(
                        text = "No meals found.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                else -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(meals) { meal ->
                            MealCard(meal)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MealCard(meal: Meal) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = rememberAsyncImagePainter(meal.thumbnail),
                contentDescription = meal.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentScale = ContentScale.Crop
            )

            Text(
                text = meal.name,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
