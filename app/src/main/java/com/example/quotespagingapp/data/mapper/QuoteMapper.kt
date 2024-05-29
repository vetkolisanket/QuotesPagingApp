package com.example.quotespagingapp.data.mapper

import com.example.quotespagingapp.data.local.QuoteEntity
import com.example.quotespagingapp.data.remote.QuoteDto
import com.example.quotespagingapp.data.remote.QuotesResponse
import com.example.quotespagingapp.domain.Quote

fun QuoteDto.toQuoteEntity(page: Int): QuoteEntity {
    return QuoteEntity(
        id = _id,
        page = page,
        quoteAuthor,
        quoteGenre, quoteText
    )
}

fun QuoteEntity.toQuote(): Quote {
    return Quote(
        id, quoteAuthor, quoteGenre, quoteText
    )
}

fun QuotesResponse.toQuoteEntities(): List<QuoteEntity> {
    return data.map { it.toQuoteEntity(pagination.currentPage) }
}