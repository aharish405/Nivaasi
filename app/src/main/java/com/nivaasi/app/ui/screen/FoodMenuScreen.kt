package com.nivaasi.app.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nivaasi.app.data.model.DayOfWeek
import com.nivaasi.app.data.model.Meal
import com.nivaasi.app.data.model.MealType
import com.nivaasi.app.ui.theme.SkyBlue
import com.nivaasi.app.ui.viewmodel.FoodMenuViewModel

@Composable
fun FoodMenuScreen(
    viewModel: FoodMenuViewModel = viewModel()
) {
    val menus by viewModel.menus.collectAsState()
    val selectedDay by viewModel.selectedDay.collectAsState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
    ) {
        // Day Tabs
        ScrollableTabRow(
            selectedTabIndex = selectedDay.ordinal,
            containerColor = Color.White,
            contentColor = SkyBlue,
            edgePadding = 16.dp
        ) {
            DayOfWeek.values().forEach { day ->
                Tab(
                    selected = selectedDay == day,
                    onClick = { viewModel.selectDay(day) },
                    text = {
                        Text(
                            text = day.name.take(3),
                            fontWeight = if (selectedDay == day) 
                                FontWeight.Bold 
                            else 
                                FontWeight.Normal
                        )
                    }
                )
            }
        }
        
        // Meals for selected day
        val selectedMenu = menus.find { it.dayOfWeek == selectedDay }
        selectedMenu?.let { menu ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(menu.meals) { meal ->
                    MealCard(meal = meal)
                }
            }
        }
    }
}

@Composable
fun MealCard(meal: Meal) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = meal.type.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = SkyBlue
                )
                Text(
                    text = meal.items,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = meal.time,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
            
            IconButton(
                onClick = { /* Edit meal */ }
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = SkyBlue
                )
            }
        }
    }
}
