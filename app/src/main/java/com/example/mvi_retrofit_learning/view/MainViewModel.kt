package com.example.mvi_retrofit_learning.view

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.mvi_retrofit_learning.api.AnimalApi
import com.example.mvi_retrofit_learning.api.AnimalRepo
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
class MainViewModel(private val repo: AnimalRepo): ViewModel() {
    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)
    var state = mutableStateOf<MainState>(MainState.Idle)
        private set

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect { collector ->
                when (collector) {
                    is MainIntent.FetchAnimals -> fetchAnimals()
                }
            }
        }
    }

    private fun fetchAnimals() {
        viewModelScope.launch {
            state.value = MainState.Loading
            state.value = try {
                MainState.Animals(repo.getAnimals())
            } catch (e: Exception) {
                MainState.Error(e.localizedMessage)
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[APPLICATION_KEY])
                // Get the AnimalApi from extras (assuming it's passed there)
                val animalApi = checkNotNull(extras[ANIMAL_API_KEY]) as AnimalApi

                // Create the repository
                val repo = AnimalRepo(animalApi)

                return MainViewModel(repo) as T
            }
        }
    }
}

// Додатково визначте ключ для AnimalApi
val ANIMAL_API_KEY = object : CreationExtras.Key<AnimalApi> {}
