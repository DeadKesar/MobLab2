package com.example.moblab2

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class Detail : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail)

        val name = intent.getStringExtra("MEAL_NAME") ?: "Unknown"
        val area = intent.getStringExtra("MEAL_AREA") ?: "Unknown"
        val thumbUrl = intent.getStringExtra("MEAL_THUMB")
        val instruction = intent.getStringExtra("MEAL_INSTRUCTIONS") ?: "Unknown"

        val nameView: TextView = findViewById(R.id.detail_name)
        val statusView: TextView = findViewById(R.id.detail_status)
        val imageView: ImageView = findViewById(R.id.detail_image)
        val instrucionView: TextView = findViewById(R.id.detail_instrucions)

        nameView.text = name
        statusView.text = "Area: $area"
        instrucionView.text = instruction

        thumbUrl?.let {
            Glide.with(this)
                .load(it)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_close_clear_cancel)
                .into(imageView)
        }
    }
}