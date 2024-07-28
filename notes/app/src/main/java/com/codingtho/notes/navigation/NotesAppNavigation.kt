package com.codingtho.notes.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.codingtho.notes.data.repository.model.Note
import com.codingtho.notes.ui.screen.Screen
import com.codingtho.notes.ui.screen.view.AddOrEditScreen
import com.codingtho.notes.ui.screen.view.MainScreen
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesAppNavigation() {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "NotesApp", fontWeight = FontWeight.Bold)
                }
            )
        },
        floatingActionButton = {
            if (currentRoute == Screen.MainScreen.route) {
                AddNote(navController = navController)
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Screen.MainScreen.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Screen.MainScreen.route) {
                MainScreen(navController = navController)
            }

            composable(
                route = Screen.AddOrEditScreen.route,
                arguments = listOf(
                    navArgument(name = "note") {
                        type = NavType.StringType
                        nullable = true
                    }
                )
            ) { navBackStackEntry ->
                val json = navBackStackEntry.arguments?.getString("note")
                val note = json?.let { Gson().fromJson(it, Note::class.java) }
                AddOrEditScreen(navController = navController, note = note)
            }
        }
    }
}

@Composable
private fun AddNote(navController: NavController) {
    FloatingActionButton(
        onClick = { navController.navigate(Screen.AddOrEditScreen.route) }
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add note"
        )
    }
}
