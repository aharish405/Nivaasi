package com.nivaasi.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nivaasi.app.data.model.DayOfWeek
import com.nivaasi.app.data.model.FoodMenu
import com.nivaasi.app.data.repository.FakeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FoodMenuViewModel : ViewModel() {
    private val repository = FakeRepository()
    
    private val _menus = MutableStateFlow<List<FoodMenu>>(emptyList())
    val menus: StateFlow<List<FoodMenu>> = _menus.asStateFlow()
    
    private val _selectedDay = MutableStateFlow(DayOfWeek.MONDAY)
    val selectedDay: StateFlow<DayOfWeek> = _selectedDay.asStateFlow()
    
    init {
        loadMenus()
    }
    
    private fun loadMenus() {
        viewModelScope.launch {
            repository.getFoodMenu().collect {
                _menus.value = it
            }
        }
    }
    
    fun selectDay(day: DayOfWeek) {
        _selectedDay.value = day
    }
}
