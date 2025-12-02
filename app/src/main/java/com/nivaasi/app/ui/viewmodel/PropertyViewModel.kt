package com.nivaasi.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nivaasi.app.data.model.Property
import com.nivaasi.app.data.repository.FakeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PropertyViewModel : ViewModel() {
    private val repository = FakeRepository()
    
    private val _property = MutableStateFlow<Property?>(null)
    val property: StateFlow<Property?> = _property.asStateFlow()
    
    private val _selectedFloorIndex = MutableStateFlow(0)
    val selectedFloorIndex: StateFlow<Int> = _selectedFloorIndex.asStateFlow()
    
    init {
        loadProperty()
    }
    
    private fun loadProperty() {
        viewModelScope.launch {
            repository.getProperty().collect {
                _property.value = it
            }
        }
    }
    
    fun selectFloor(index: Int) {
        _selectedFloorIndex.value = index
    }
}
