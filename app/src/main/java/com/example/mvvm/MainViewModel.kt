package com.example.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback

class MainViewModel : ViewModel() {
    val songData = MutableLiveData<Song>()
    // Can also be written as:
    // val songData: LiveData<Song> = MutableLiveData<Song>()

    fun searchSong(term: String) {
        ApiManager.service.search(term, 1).enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                songData.postValue(null)
            }

            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        songData.postValue(it.results.firstOrNull())
                    }
                } else {
                    songData.postValue(null)
                }
            }
        })
    }
}