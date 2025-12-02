package com.nivaasi.app.navigation

sealed class Screen(val route: String) {
    object Dashboard : Screen("dashboard")
    object PropertyManagement : Screen("property_management")
    object Tenants : Screen("tenants")
    object Transactions : Screen("transactions")
    object FoodMenu : Screen("food_menu")
    object Profile : Screen("profile")
}
