package com.example.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val provider = ViewModelProvider(this)
        viewModel = provider.get(MainViewModel::class.java)

        observeViewModel()

        initView()
    }

    fun observeViewModel() {
        viewModel.songData.observe(this, Observer {
            if (it != null) {
                text_results.text = "${it.artistName}\n${it.trackName}"
            } else {
                text_results.text = getString(R.string.error)
            }
        })
    }

    fun initView() {
        btn_search.setOnClickListener {
            searchSong()
        }
    }

    fun searchSong() {
        val term = text_song_name.text.toString()
        if (term.isNotEmpty()) {
            viewModel.searchSong(term)
        }
    }
}
