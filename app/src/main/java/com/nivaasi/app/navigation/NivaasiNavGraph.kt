package com.nivaasi.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nivaasi.app.ui.screen.*

@Composable
fun NivaasiNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route,
        modifier = modifier
    ) {
        composable(Screen.Dashboard.route) {
            DashboardScreen()
        }
        
        composable(Screen.PropertyManagement.route) {
            PropertyManagementScreen()
        }
        
        composable(Screen.Tenants.route) {
            TenantListScreen()
        }
        
        composable(Screen.Transactions.route) {
            TransactionsScreen()
        }
        
        composable(Screen.FoodMenu.route) {
            FoodMenuScreen()
        }
        
        composable(Screen.Profile.route) {
            ProfileScreen()
        }
    }
}
