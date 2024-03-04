package com.example.kotlincourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.kotlincourse.ui.JobSearchingApp
import com.example.kotlincourse.ui.JobSearchingViewModel
import com.example.kotlincourse.ui.theme.JobSearchingClientTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var jobSearchingViewModel: JobSearchingViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContainer.inject(this)
        setContent {
            JobSearchingClientTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    JobSearchingApp(rememberNavController(), jobSearchingViewModel)
                }

            }
        }
    }
}

