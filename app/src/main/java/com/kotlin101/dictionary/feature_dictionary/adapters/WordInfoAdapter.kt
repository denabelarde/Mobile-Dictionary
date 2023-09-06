package com.kotlin101.dictionary.feature_dictionary.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import com.kotlin101.dictionary.MainActivity
import com.kotlin101.dictionary.R
import com.kotlin101.dictionary.feature_dictionary.domain.model.WordInfo


class WordInfoAdapter(private val wordInfoList: List<WordInfo>, private val mainActivity: MainActivity) : RecyclerView.Adapter<WordInfoAdapter.ViewHolder>() {

    private val viewPool = RecycledViewPool()
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val wordTextView: TextView = view.findViewById(R.id.wordTextView)
        val definitionTextView:TextView = view.findViewById(R.id.definitionTextView)
        val exampleTextView:TextView = view.findViewById(R.id.exampleTextView)
        val partOfSpeechTextView: TextView = view.findViewById(R.id.partOfSpeechTextView)
        val phoneticsRecyclerView: RecyclerView = view.findViewById(R.id.phonetics_recycler)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.word_info_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val layoutManager = LinearLayoutManager(
            viewHolder.phoneticsRecyclerView
                .context,
            LinearLayoutManager.VERTICAL,
            false
        )
        layoutManager.initialPrefetchItemCount = wordInfoList[position].phonetics.size;

        val childItemAdapter = PhoneticsAdapter(
            wordInfoList[position].phonetics, mainActivity
        )
        viewHolder.phoneticsRecyclerView.layoutManager = layoutManager
        viewHolder.phoneticsRecyclerView.adapter = childItemAdapter
        viewHolder.phoneticsRecyclerView
            .setRecycledViewPool(viewPool)
        viewHolder.wordTextView.text = wordInfoList[position].word
        wordInfoList[position].meanings.forEach{
            viewHolder.partOfSpeechTextView.text = it.partOfSpeech
            viewHolder.definitionTextView.text = it.definitions.firstOrNull()?.definition
            viewHolder.exampleTextView.text = it.definitions.firstOrNull()?.example
        }
    }

    override fun getItemCount() = wordInfoList.size


}