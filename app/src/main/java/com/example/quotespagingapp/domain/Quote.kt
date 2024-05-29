package com.example.quotespagingapp.domain

data class Quote(
    val id: String,
    val quoteAuthor: String,
    val quoteGenre: String,
    val quoteText: String
)