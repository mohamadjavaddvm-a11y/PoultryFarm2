package com.example.poultryfarm.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.poultryfarm.MainViewModel

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Home : Screen("home", "خانه", Icons.Filled.Home)
    object Halls : Screen("halls", "سالن‌ها", Icons.Filled.List)
    object Vaccine : Screen("vaccine", "واکسن", Icons.Filled.Info)
    object Lighting : Screen("lighting", "نور", Icons.Filled.DateRange)
}

val bottomItems = listOf(Screen.Home, Screen.Halls, Screen.Vaccine, Screen.Lighting)

@Composable
fun AppNavigation(viewModel: MainViewModel) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = backStackEntry?.destination?.route
                bottomItems.forEach { screen ->
                    NavigationBarItem(
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        },
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Screen.Home.route) { HomeScreen(viewModel, navController) }
            composable(Screen.Halls.route) { HallsScreen(viewModel) }
            composable(Screen.Vaccine.route) { VaccineScreen(viewModel) }
            composable(Screen.Lighting.route) { LightingScreen(viewModel) }
            composable("daily_entry") { DailyEntryScreen(viewModel, navController) }
        }
    }
}
