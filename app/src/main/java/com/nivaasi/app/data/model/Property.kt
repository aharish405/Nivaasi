package com.nivaasi.app.data.model

data class Property(
    val id: String,
    val name: String,
    val address: String,
    val floors: List<Floor>
)

data class Floor(
    val id: String,
    val name: String,
    val floorNumber: Int,
    val rooms: List<Room>
)

data class Room(
    val id: String,
    val roomNumber: String,
    val capacity: Int,
    val beds: List<Bed>
) {
    val availableCount: Int
        get() = beds.count { it.status == BedStatus.AVAILABLE }
    
    val occupiedCount: Int
        get() = beds.count { it.status == BedStatus.OCCUPIED }
}

data class Bed(
    val id: String,
    val bedNumber: String,
    val status: BedStatus,
    val tenant: Tenant? = null
)

enum class BedStatus {
    AVAILABLE,
    OCCUPIED,
    NOTICE,
    VACATED,
    BLOCKED
}
