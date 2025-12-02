package com.nivaasi.app.data.model

data class DashboardSummary(
    val availableBeds: Int,
    val unpaidTenants: Int,
    val expenses: Double,
    val currentRentalAmount: Double,
    val outstandingAmount: Double,
    val securityDeposit: Double,
    val collectedAmount: Double,
    val pendingAmount: Double
)

data class OccupancyStats(
    val totalBeds: Int,
    val available: Int,
    val occupied: Int,
    val vacated: Int,
    val notice: Int,
    val blocked: Int
)
