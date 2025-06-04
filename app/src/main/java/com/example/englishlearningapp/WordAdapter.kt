package com.example.englishlearningapp

import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class WordAdapter(private val words: List<Word>) : RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    private var textToSpeech: TextToSpeech? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_word, parent, false)
        // Initialize TextToSpeech here when the context is available
        if (textToSpeech == null) {
            textToSpeech = TextToSpeech(parent.context) {
                if (it == TextToSpeech.SUCCESS) {
                    textToSpeech?.language = Locale.US
                }
            }
        }
        return WordViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word = words[position]
        holder.wordText.text = word.text
        
        // Get the image resource ID dynamically
        val imageResId = holder.itemView.context.resources.getIdentifier(
            word.imageResName, "drawable", holder.itemView.context.packageName)
        if (imageResId != 0) {
            holder.wordImage.setImageResource(imageResId)
        } else {
            holder.wordImage.setImageResource(R.drawable.placeholder_image) // Fallback image
        }

        holder.pronounceButton.setOnClickListener {
            textToSpeech?.speak(word.text, TextToSpeech.QUEUE_FLUSH, null, null)
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