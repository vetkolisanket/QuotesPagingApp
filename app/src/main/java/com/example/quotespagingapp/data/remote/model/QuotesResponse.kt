package com.example.quotespagingapp.data.remote.model

data class QuotesResponse(
    val `data`: List<QuoteDto>,
    val message: String,
    val pagination: Pagination,
    val statusCode: Int,
    val totalQuotes: Int
)