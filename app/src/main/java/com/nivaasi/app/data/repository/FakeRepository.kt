package com.nivaasi.app.data.repository

import com.nivaasi.app.data.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeRepository {
    
    fun getProperty(): Flow<Property> = flowOf(
        Property(
            id = "1",
            name = "Nivaasi PG",
            address = "123 Main Street, City",
            floors = listOf(
                Floor(
                    id = "f1",
                    name = "1st Floor",
                    floorNumber = 1,
                    rooms = listOf(
                        Room(
                            id = "r101",
                            roomNumber = "101",
                            capacity = 4,
                            beds = listOf(
                                Bed("b1", "1", BedStatus.OCCUPIED),
                                Bed("b2", "2", BedStatus.OCCUPIED),
                                Bed("b3", "3", BedStatus.AVAILABLE),
                                Bed("b4", "4", BedStatus.NOTICE)
                            )
                        ),
                        Room(
                            id = "r102",
                            roomNumber = "102",
                            capacity = 3,
                            beds = listOf(
                                Bed("b5", "1", BedStatus.OCCUPIED),
                                Bed("b6", "2", BedStatus.AVAILABLE),
                                Bed("b7", "3", BedStatus.AVAILABLE)
                            )
                        ),
                        Room(
                            id = "r103",
                            roomNumber = "103",
                            capacity = 2,
                            beds = listOf(
                                Bed("b8", "1", BedStatus.BLOCKED),
                                Bed("b9", "2", BedStatus.VACATED)
                            )
                        )
                    )
                ),
                Floor(
                    id = "f2",
                    name = "2nd Floor",
                    floorNumber = 2,
                    rooms = listOf(
                        Room(
                            id = "r201",
                            roomNumber = "201",
                            capacity = 4,
                            beds = listOf(
                                Bed("b10", "1", BedStatus.AVAILABLE),
                                Bed("b11", "2", BedStatus.AVAILABLE),
                                Bed("b12", "3", BedStatus.AVAILABLE),
                                Bed("b13", "4", BedStatus.AVAILABLE)
                            )
                        ),
                        Room(
                            id = "r202",
                            roomNumber = "202",
                            capacity = 3,
                            beds = listOf(
                                Bed("b14", "1", BedStatus.OCCUPIED),
                                Bed("b15", "2", BedStatus.OCCUPIED),
                                Bed("b16", "3", BedStatus.NOTICE)
                            )
                        )
                    )
                )
            )
        )
    )
    
    fun getTenants(): Flow<List<Tenant>> = flowOf(
        listOf(
            Tenant(
                id = "t1",
                name = "Rahul Kumar",
                phone = "+91 9876543210",
                email = "rahul@example.com",
                roomNumber = "101",
                floorNumber = 1,
                rentAmount = 8000.0,
                nextDueDate = "2023-12-05",
                hasDownloadedApp = false
            ),
            Tenant(
                id = "t2",
                name = "Priya Sharma",
                phone = "+91 9876543211",
                email = "priya@example.com",
                roomNumber = "102",
                floorNumber = 1,
                rentAmount = 7500.0,
                nextDueDate = "2023-12-10",
                hasDownloadedApp = true
            ),
            Tenant(
                id = "t3",
                name = "Amit Patel",
                phone = "+91 9876543212",
                email = "amit@example.com",
                roomNumber = "201",
                floorNumber = 2,
                rentAmount = 8500.0,
                nextDueDate = "2023-12-01",
                hasDownloadedApp = true
            ),
            Tenant(
                id = "t4",
                name = "Sneha Reddy",
                phone = "+91 9876543213",
                email = "sneha@example.com",
                roomNumber = "202",
                floorNumber = 2,
                rentAmount = 7000.0,
                nextDueDate = "2023-12-15",
                hasDownloadedApp = false
            )
        )
    )
    
    fun getTransactions(): Flow<List<Transaction>> = flowOf(
        listOf(
            Transaction(
                id = "tr1",
                type = TransactionType.RENT,
                amount = 8000.0,
                date = "2023-11-05",
                status = TransactionStatus.COLLECTED,
                description = "Rent - Rahul Kumar - Room 101"
            ),
            Transaction(
                id = "tr2",
                type = TransactionType.RENT,
                amount = 7500.0,
                date = "2023-11-10",
                status = TransactionStatus.PENDING,
                description = "Rent - Priya Sharma - Room 102"
            ),
            Transaction(
                id = "tr3",
                type = TransactionType.DEPOSIT,
                amount = 15000.0,
                date = "2023-11-01",
                status = TransactionStatus.COLLECTED,
                description = "Security Deposit - Amit Patel"
            ),
            Transaction(
                id = "tr4",
                type = TransactionType.EXPENSE,
                amount = 5000.0,
                date = "2023-11-15",
                status = TransactionStatus.COLLECTED,
                description = "Maintenance - Plumbing Work"
            ),
            Transaction(
                id = "tr5",
                type = TransactionType.RENT,
                amount = 8500.0,
                date = "2023-11-01",
                status = TransactionStatus.COLLECTED,
                description = "Rent - Amit Patel - Room 201"
            )
        )
    )
    
    fun getFoodMenu(): Flow<List<FoodMenu>> = flowOf(
        DayOfWeek.values().map { day ->
            FoodMenu(
                dayOfWeek = day,
                meals = listOf(
                    Meal(MealType.BREAKFAST, "Idli, Sambar, Chutney", "7:00 AM - 9:00 AM"),
                    Meal(MealType.LUNCH, "Rice, Dal, Sabzi, Roti, Salad", "12:00 PM - 2:00 PM"),
                    Meal(MealType.SNACKS, "Tea, Biscuits", "4:00 PM - 5:00 PM"),
                    Meal(MealType.DINNER, "Roti, Sabzi, Dal, Rice", "7:00 PM - 9:00 PM")
                )
            )
        }
    )
    
    fun getDashboardSummary(): Flow<DashboardSummary> = flowOf(
        DashboardSummary(
            availableBeds = 8,
            unpaidTenants = 2,
            expenses = 5000.0,
            currentRentalAmount = 31000.0,
            outstandingAmount = 15500.0,
            securityDeposit = 60000.0,
            collectedAmount = 15500.0,
            pendingAmount = 15500.0
        )
    )
    
    fun getOccupancyStats(): Flow<OccupancyStats> = flowOf(
        OccupancyStats(
            totalBeds = 16,
            available = 8,
            occupied = 5,
            vacated = 1,
            notice = 2,
            blocked = 1
        )
    )
}
