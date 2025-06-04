package com.englishlearningapp

import com.englishlearningapp.Word

data class Category(
    val name: String,
    val imageResId: Int,
    val words: List<Word>
) 