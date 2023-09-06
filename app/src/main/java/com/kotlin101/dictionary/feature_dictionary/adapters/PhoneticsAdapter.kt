package com.kotlin101.dictionary.feature_dictionary.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlin101.dictionary.MainActivity
import com.kotlin101.dictionary.R
import com.kotlin101.dictionary.feature_dictionary.domain.model.Phonetic


class PhoneticsAdapter(private val phonetics: List<Phonetic>, private val mainActivity: MainActivity) : RecyclerView.Adapter<PhoneticsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): ViewHolder {

        // Here we inflate the corresponding
        // layout of the child item
        val view: View = LayoutInflater
            .from(viewGroup.context)
            .inflate(
                R.layout.phonetic_item,
                viewGroup, false
            )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        viewHolder: ViewHolder,
        position: Int
    ) {
        val phonetic: Phonetic = phonetics[position]
        viewHolder.phoneticTextView.text = phonetic.text
        viewHolder.audioIcon.setOnClickListener{
            mainActivity.playAudio(phonetic.text, phonetic.audio)
        }
    }

    override fun getItemCount(): Int {
        return phonetics.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val phoneticTextView: TextView = view.findViewById(R.id.phoneticTextView)
        val audioIcon: ImageView = view.findViewById(R.id.audioIcon)
    }

}