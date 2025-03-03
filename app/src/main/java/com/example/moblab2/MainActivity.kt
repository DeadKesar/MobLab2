package com.example.moblab2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list: RecyclerView = findViewById(R.id.list)
        val adapter = ContactsAdapter(contacts(), this::onContactItemClicked)
        list.adapter = adapter
    }
    private fun onContactItemClicked(item: ContactItem) {
        Toast.makeText(
            this, "Contact: ${item.name} clicked!", Toast.LENGTH_SHORT
        ).show()
    }
}
