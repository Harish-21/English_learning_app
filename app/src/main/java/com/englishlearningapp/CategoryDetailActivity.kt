package com.englishlearningapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.englishlearningapp.databinding.ActivityCategoryDetailBinding
import com.englishlearningapp.Word
import kotlin.collections.emptyList
import kotlin.collections.listOf

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
        wordAdapter = WordAdapter(words, this@CategoryDetailActivity)
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
                Word("Plate", R.drawable.plate),
                Word("Spoon", R.drawable.spoon),
                Word("Fork", R.drawable.fork),
                Word("Knife", R.drawable.knife)
            )
            "Furnitures" -> listOf(
                Word("Chair", R.drawable.chair),
                Word("Table", R.drawable.table),
                Word("Bed", R.drawable.bed),
                Word("Sofa", R.drawable.sofa),
                Word("Lamp", R.drawable.placeholder_image),
                Word("Wardrobe", R.drawable.placeholder_image),
                Word("Desk", R.drawable.placeholder_image),
                Word("Bookshelf", R.drawable.placeholder_image),
                Word("Rug", R.drawable.placeholder_image),
                Word("Curtains", R.drawable.placeholder_image)
            )
            "Automobiles" -> listOf(
                Word("Car", R.drawable.car),
                Word("Tire", R.drawable.tire),
                Word("Engine", R.drawable.engine),
                Word("Steering Wheel", R.drawable.steering_wheel),
                Word("Wheel", R.drawable.placeholder_image),
                Word("Door", R.drawable.placeholder_image),
                Word("Headlight", R.drawable.placeholder_image),
                Word("Trunk", R.drawable.placeholder_image),
                Word("Hood", R.drawable.placeholder_image)
            )
            else -> emptyList()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::wordAdapter.isInitialized) {
            wordAdapter.shutdownTextToSpeech()
        }
    }
} 