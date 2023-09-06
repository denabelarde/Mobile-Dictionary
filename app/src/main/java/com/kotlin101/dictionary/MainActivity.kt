package com.kotlin101.dictionary

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.kotlin101.dictionary.feature_dictionary.adapters.WordInfoAdapter
import com.kotlin101.dictionary.feature_dictionary.presentation.WordInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.IOException


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
                            val wordInfoList = it.wordInfoItems

                            val customAdapter = WordInfoAdapter(wordInfoList, this@MainActivity)
                            val recyclerView: RecyclerView = findViewById(R.id.recyclerviewnow)
                            recyclerView.adapter = customAdapter
                        }
                    }
                }
            }
        }

    }

    private var mediaPlayer: MediaPlayer? = null

    fun playAudio(word: String, audioUrl: String) {
        // initializing media player
        mediaPlayer = MediaPlayer()

        // below line is use to set the audio
        // stream type for our media player.
        mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)

        // below line is use to set our
        // url to our media player.
        try {
            mediaPlayer!!.setDataSource(audioUrl)
            // below line is use to prepare
            // and start our media player.
            mediaPlayer!!.prepare()
            mediaPlayer!!.start()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        // below line is use to display a toast message.
        Toast.makeText(this, "Playing audio for $word", Toast.LENGTH_SHORT).show()
    }
}