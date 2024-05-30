package com.example.quotespagingapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuoteEntity(
    @PrimaryKey
    val id: Int,
    val page: Int,
    val quoteAuthor: String,
    val quoteGenre: String,
    val quoteText: String
)