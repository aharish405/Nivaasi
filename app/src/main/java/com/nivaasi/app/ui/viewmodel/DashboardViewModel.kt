package com.nivaasi.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nivaasi.app.data.model.DashboardSummary
import com.nivaasi.app.data.model.OccupancyStats
import com.nivaasi.app.data.repository.FakeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {
    private val repository = FakeRepository()
    
    private val _summary = MutableStateFlow<DashboardSummary?>(null)
    val summary: StateFlow<DashboardSummary?> = _summary.asStateFlow()
    
    private val _occupancy = MutableStateFlow<OccupancyStats?>(null)
    val occupancy: StateFlow<OccupancyStats?> = _occupancy.asStateFlow()
    
    private val _isOccupancyExpanded = MutableStateFlow(false)
    val isOccupancyExpanded: StateFlow<Boolean> = _isOccupancyExpanded.asStateFlow()
    
    init {
        loadData()
    }
    
    private fun loadData() {
        viewModelScope.launch {
            repository.getDashboardSummary().collect {
                _summary.value = it
            }
        }
        viewModelScope.launch {
            repository.getOccupancyStats().collect {
                _occupancy.value = it
            }
        }
    }
    
    fun toggleOccupancy() {
        _isOccupancyExpanded.value = !_isOccupancyExpanded.value
    }
}
