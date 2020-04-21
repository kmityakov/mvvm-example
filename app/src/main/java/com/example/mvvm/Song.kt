package com.example.mvvm

class Response(
    val resultCount: Int,
    val results: List<Song>
)

class Song(
    val artistName: String,
    val trackName: String
)