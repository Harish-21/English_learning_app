package com.example.englishlearningapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.englishlearningapp.databinding.ActivityCategoryDetailBinding

class CategoryDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryDetailBinding
    private lateinit var wordAdapter: WordAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val category = intent.getStringExtra("category") ?: ""
        binding.tvCategoryTitle.text = category

        setupRecyclerView(category)
    }

    private fun setupRecyclerView(category: String) {
        val words = getWordsForCategory(category)
        wordAdapter = WordAdapter(words)
        binding.rvWords.apply {
            layoutManager = LinearLayoutManager(this@CategoryDetailActivity)
            adapter = wordAdapter
        }
    }

    private fun getWordsForCategory(category: String): List<Word> {
        // This is where you would fetch words based on the category
        // For now, I'll return dummy data.
        return when (category) {
            "Cooking" -> listOf(
                Word("Plate", "plate_placeholder"),
                Word("Spoon", "spoon_placeholder"),
                Word("Fork", "fork_placeholder"),
                Word("Knife", "knife_placeholder")
            )
            "Furniture" -> listOf(
                Word("Chair", "chair_placeholder"),
                Word("Table", "table_placeholder"),
                Word("Bed", "bed_placeholder"),
                Word("Sofa", "sofa_placeholder")
            )
            "Automotors" -> listOf(
                Word("Car", "car_placeholder"),
                Word("Tire", "tire_placeholder"),
                Word("Engine", "engine_placeholder"),
                Word("Steering Wheel", "steering_wheel_placeholder")
            )
            else -> emptyList()
        }
    }
} 