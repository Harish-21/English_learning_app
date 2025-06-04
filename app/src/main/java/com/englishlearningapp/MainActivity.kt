package com.englishlearningapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.englishlearningapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.llCooking.setOnClickListener { 
            navigateToCategory("Cooking")
        }

        binding.llFurniture.setOnClickListener { 
            navigateToCategory("Furniture")
        }

        binding.llAutomotors.setOnClickListener { 
            navigateToCategory("Automobiles")
        }
    }

    private fun navigateToCategory(category: String) {
        val intent = Intent(this, CategoryDetailActivity::class.java)
        intent.putExtra("category", category)
        startActivity(intent)
    }
}