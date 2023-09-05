package com.kotlin101.dictionary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.kotlin101.dictionary.feature_dictionary.adapters.DictionaryAdapter
import com.kotlin101.dictionary.feature_dictionary.presentation.WordInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WordInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewSearch: SearchView = findViewById(R.id.search)

        viewSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.onSearch(newText)
                return true
            }
        })


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.state.collect{
                        if(it.wordInfoItems.isNotEmpty()){
                            var wordInfoList = it.wordInfoItems

                            val customAdapter = DictionaryAdapter(wordInfoList)
                            val recyclerView: RecyclerView = findViewById(R.id.recyclerviewnow)
                            recyclerView.adapter = customAdapter
                        }
                    }
                }
            }
        }

    }
}