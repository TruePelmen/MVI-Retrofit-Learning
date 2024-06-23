package com.example.mvi_retrofit_learning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.MutableCreationExtras
import com.example.mvi_retrofit_learning.api.AnimalApi
import com.example.mvi_retrofit_learning.api.AnimalService
import com.example.mvi_retrofit_learning.ui.theme.MVI_Retrofit_LearningTheme
import com.example.mvi_retrofit_learning.view.ANIMAL_API_KEY
import com.example.mvi_retrofit_learning.view.MainViewModel

class MainActivity : ComponentActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = MainViewModel.Factory.create(MainViewModel::class.java, createExtras())
        enableEdgeToEdge()
        setContent {
            MVI_Retrofit_LearningTheme {
                Surface(color = MaterialTheme.colorScheme.background){

                }
            }
        }
    }

    private fun createExtras(): CreationExtras {
        return MutableCreationExtras().apply {
            this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] = application
            this[ANIMAL_API_KEY] = AnimalService.api
        }
    }
}