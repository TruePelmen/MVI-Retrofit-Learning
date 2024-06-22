package com.example.mvi_retrofit_learning.view

sealed class MainIntent {
    object FetchAnimals: MainIntent()
}