package com.example.taskmanager

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object AddEditScreen: Screen(route = "add_edit_screen")
}