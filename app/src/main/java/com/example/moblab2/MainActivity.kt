package com.example.moblab2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list: RecyclerView = findViewById(R.id.list)
        fetchMeals{ meals ->
            val adapter = ContactsAdapter(meals, this::onContactItemClicked)
            list.adapter = adapter
        }
    }
    private fun onContactItemClicked(item: ContactItem) {
        val intent = Intent(this, Detail::class.java).apply {
            putExtra("MEAL_NAME", item.strMeal)
            putExtra("MEAL_AREA", item.strArea ?: "Unknown")
            putExtra("MEAL_THUMB", item.strMealThumb)
            putExtra("MEAL_INSTRUCTIONS", item.strInstructions)
        }
        startActivity(intent)

        Toast.makeText(this, "Contact: ${item.strMeal} clicked!", Toast.LENGTH_SHORT).show()
    }

    private fun fetchMeals(callback: (List<ContactItem>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val url = URL("https://www.themealdb.com/api/json/v1/1/search.php?f=b")
                val connection = url.openConnection()
                val jsonString = connection.getInputStream().bufferedReader().use { it.readText() }
                val jsonObject = JSONObject(jsonString)
                val mealsArray = jsonObject.getJSONArray("meals")
                val meals = mutableListOf<ContactItem>()

                for (i in 0 until mealsArray.length()) {
                    val mealJson = mealsArray.getJSONObject(i)
                    val meal = ContactItem(
                        strMeal = mealJson.getString("strMeal"),
                        strArea = mealJson.optString("strArea", "Где-то"),
                        strMealThumb = mealJson.optString("strMealThumb", ""),
                        strInstructions = mealJson.optString("strInstructions", "I don't know"),
                        strCategory = mealJson.optString("strCategory", "Волшебное")
                    )
                    meals.add(meal)
                }

                withContext(Dispatchers.Main) {
                    callback(meals)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Error fetching meals: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}
