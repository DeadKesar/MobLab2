package com.example.moblab2

/**
 * Модель контакта
 */
data class ContactItem(
    val name: String,
    val isOnline: Boolean
)
/**
 * Функция для создания списка контактов.
 */
fun contacts() : List<ContactItem> = MutableList(20) { index ->
    ContactItem(
        name = "Person $index",
        isOnline = index > 10
    )
}