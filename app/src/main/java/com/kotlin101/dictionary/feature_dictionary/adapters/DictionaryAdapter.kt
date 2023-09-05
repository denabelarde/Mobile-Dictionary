package com.kotlin101.dictionary.feature_dictionary.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlin101.dictionary.R
import com.kotlin101.dictionary.feature_dictionary.domain.model.WordInfo


class DictionaryAdapter(private val wordInfoList: List<WordInfo>) : RecyclerView.Adapter<DictionaryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val wordTextView: TextView = view.findViewById(R.id.wordTextView)
        val phoneticTextView: TextView = view.findViewById(R.id.phoneticTextView)
        val definitionTextView:TextView = view.findViewById(R.id.definitionTextView)
        val exampleTextView:TextView = view.findViewById(R.id.exampleTextView)
        val partOfSpeechTextView: TextView = view.findViewById(R.id.partOfSpeechTextView)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.textview_row, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.wordTextView.text = wordInfoList[position].word
        viewHolder.phoneticTextView.text = wordInfoList[position].phonetic

        wordInfoList[position].meanings.forEach{
            viewHolder.partOfSpeechTextView.text = it.partOfSpeech
            viewHolder.definitionTextView.text = it.definitions.firstOrNull()?.definition
            viewHolder.exampleTextView.text = it.definitions.firstOrNull()?.example
        }
    }

    override fun getItemCount() = wordInfoList.size
}