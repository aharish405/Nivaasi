package com.nivaasi.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nivaasi.app.data.model.Tenant
import com.nivaasi.app.data.repository.FakeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TenantViewModel : ViewModel() {
    private val repository = FakeRepository()
    
    private val _tenants = MutableStateFlow<List<Tenant>>(emptyList())
    val tenants: StateFlow<List<Tenant>> = _tenants.asStateFlow()
    
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()
    
    private val _filteredTenants = MutableStateFlow<List<Tenant>>(emptyList())
    val filteredTenants: StateFlow<List<Tenant>> = _filteredTenants.asStateFlow()
    
    init {
        loadTenants()
    }
    
    private fun loadTenants() {
        viewModelScope.launch {
            repository.getTenants().collect { tenantList ->
                _tenants.value = tenantList
                filterTenants()
            }
        }
    }
    
    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        filterTenants()
    }
    
    private fun filterTenants() {
        val query = _searchQuery.value.lowercase()
        _filteredTenants.value = if (query.isEmpty()) {
            _tenants.value
        } else {
            _tenants.value.filter {
                it.name.lowercase().contains(query) || it.phone.contains(query)
            }
        }
    }
}
