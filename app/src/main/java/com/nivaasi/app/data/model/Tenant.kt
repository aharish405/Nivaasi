package com.nivaasi.app.data.model

data class Tenant(
    val id: String,
    val name: String,
    val phone: String,
    val email: String,
    val profileImage: String? = null,
    val roomNumber: String,
    val floorNumber: Int,
    val rentAmount: Double,
    val nextDueDate: String,
    val hasDownloadedApp: Boolean = false
)
