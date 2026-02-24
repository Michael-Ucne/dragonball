package com.phantomshard.dragonball

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.phantomshard.dragonball.ui.detail.DetailScreen
import com.phantomshard.dragonball.ui.detail.DetailViewModel
import com.phantomshard.dragonball.ui.list.ListScreen
import com.phantomshard.dragonball.ui.theme.DragonBallTheme
import com.phantomshard.dragonball.util.Screen
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DragonBallTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.List
    ) {
        composable<Screen.List> {
            ListScreen(
                onCharacterClick = { characterId ->
                    navController.navigate(Screen.Detail(characterId))
                }
            )
        }
        composable<Screen.Detail> {
            val viewModel: DetailViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()
            
            DetailScreen(
                state = state,
                onBack = { navController.popBackStack() }
            )
        }
    }
}