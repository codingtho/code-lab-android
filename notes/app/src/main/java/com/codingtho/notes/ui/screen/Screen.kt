package com.codingtho.notes.ui.screen

sealed class Screen(
    val route: String
) {
    data object MainScreen: Screen(route = "main")
    data object AddOrEditScreen: Screen(route = "add_or_edit?note={note}")
}
