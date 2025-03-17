package com.woosung.compose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.woosung.compose.test.ui.theme.ComposeTestTheme
import com.woosung.compose.presentation.screen.main.MainScreen
import com.woosung.compose.presentation.screen.search.brand.BrandScreen
import com.woosung.compose.presentation.screen.search.model.ModelScreen
import com.woosung.compose.presentation.screen.search.modelgroup.ModelGroupScreen
import com.woosung.compose.presentation.theme.ComposeTestTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeTestTheme {
    }
}


