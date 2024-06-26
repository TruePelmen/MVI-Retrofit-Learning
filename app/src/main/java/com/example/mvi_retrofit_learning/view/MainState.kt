package com.example.mvi_retrofit_learning.view

import com.example.mvi_retrofit_learning.model.Animal

sealed class MainState {
    object Idle: MainState()
    object Loading: MainState()
    data class Animals(val animals: List<Animal>): MainState()
    data class Error(val error: String?): MainState()
}