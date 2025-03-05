package com.example.moblab2

/**
 * Модель еды
 */
data class MealItem(
    val strMeal: String,
    val strArea: String?,
    val strMealThumb: String?,
    val strInstructions: String,
    val strCategory: String?
)
/**
 * Функция для создания списка контактов.

fun contacts() : List<ContactItem> = MutableList(20) { index ->
    ContactItem(
        name = "Person $index"
        sOnline = index > 10
    )
}*/