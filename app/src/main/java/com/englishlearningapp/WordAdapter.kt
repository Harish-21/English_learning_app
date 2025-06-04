package com.englishlearningapp

import android.content.Context
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale
import android.os.Bundle
import android.util.Log

class WordAdapter(private val words: List<Word>, private val context: Context) : RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    private var textToSpeech: TextToSpeech? = null
    private var isTtsReady = false

    init {
        textToSpeech = TextToSpeech(context) {
            if (it == TextToSpeech.SUCCESS) {
                val result = textToSpeech?.setLanguage(Locale.US)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("WordAdapter", "Language not supported or missing data")
                    isTtsReady = false
                } else {
                    Log.d("WordAdapter", "TextToSpeech initialized successfully")
                    isTtsReady = true
                }
            } else {
                Log.e("WordAdapter", "TextToSpeech initialization failed")
                isTtsReady = false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_word, parent, false)
        return WordViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word = words[position]
        holder.wordText.text = word.name
        
        // Get the image resource ID dynamically
        val imageResId = word.imageResId
        if (imageResId != 0) {
            holder.wordImage.setImageResource(imageResId)
        } else {
            holder.wordImage.setImageResource(R.drawable.placeholder_image) // Fallback image
        }

        holder.pronounceButton.setOnClickListener {
            if (isTtsReady) {
                textToSpeech?.speak(word.name, TextToSpeech.QUEUE_FLUSH, Bundle.EMPTY, null)
            } else {
                Log.e("WordAdapter", "TextToSpeech not ready, cannot pronounce word.")
            }
        }
    }

    override fun getItemCount(): Int = words.size

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordImage: ImageView = itemView.findViewById(R.id.iv_word_image)
        val wordText: TextView = itemView.findViewById(R.id.tv_word_text)
        val pronounceButton: ImageButton = itemView.findViewById(R.id.btn_pronounce)
    }

    // Remember to shut down TextToSpeech when the activity is destroyed to avoid memory leaks
    fun shutdownTextToSpeech() {
        textToSpeech?.stop()
        textToSpeech?.shutdown()
    }
} 