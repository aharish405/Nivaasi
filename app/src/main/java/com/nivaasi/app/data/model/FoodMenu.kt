package com.nivaasi.app.data.model

data class FoodMenu(
    val dayOfWeek: DayOfWeek,
    val meals: List<Meal>
)

data class Meal(
    val type: MealType,
    val items: String,
    val time: String
)

enum class DayOfWeek {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

enum class MealType {
    BREAKFAST, LUNCH, SNACKS, DINNER
}
