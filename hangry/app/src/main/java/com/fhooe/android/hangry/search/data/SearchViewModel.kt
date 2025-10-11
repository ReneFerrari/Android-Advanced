package com.fhooe.android.hangry.search.data

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fhooe.android.hangry.api.repository.SearchRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class SearchViewModel(
    val searchRepository: SearchRepository
): ViewModel() {

    private var query: MutableStateFlow<String> = MutableStateFlow("")
    private var mealList: MutableStateFlow<List<Meal>> = MutableStateFlow(emptyList())

    private val _uiState = MutableStateFlow(SearchScreenState())
    val uiState: StateFlow<SearchScreenState> = _uiState.asStateFlow()

    init {
        observeUiState()

        viewModelScope.launch {
            query
                .debounce(150)
                .filter { it.isNotEmpty() }
                .collect { searchQuery ->
                    searchRepository
                        .searchMeals(searchQuery)
                        .onSuccess { apiMeals ->
                            val meals = apiMeals.mapNotNull { apiMeal ->
                                if (apiMeal.name == null ) {
                                    null
                                } else {
                                    Meal(
                                        name = apiMeal.name ?: "",
                                        thumbnail = apiMeal.thumbnail ?: ""
                                    )
                                }
                            }

                            mealList.value = meals
                            Log.d("SearchViewModel", "findMeals: Found ${meals.size} meals")
                        }
                        .onFailure { error ->
                            Log.e("SearchViewModel", "Error searching meals: ${error.message}")
                        }
                }
        }
    }

    private fun observeUiState() {
        viewModelScope.launch {
            combine(
                query,
                mealList
            ) { query, meals ->
                SearchScreenState(
                    query = query,
                    meals = meals
                )
            }.collect {
                _uiState.value = it
            }
        }
    }

    fun findMeals(query: String) {
        this.query.value = query
    }
}