package com.example.quotespagingapp.data.mapper

import com.example.quotespagingapp.AppModule
import com.example.quotespagingapp.data.local.QuoteEntity
import com.example.quotespagingapp.data.remote.model.QuoteDto
import com.example.quotespagingapp.data.remote.model.QuotesResponse
import com.example.quotespagingapp.domain.Quote

fun QuoteDto.toQuoteEntity(page: Int, index: Int): QuoteEntity {
    return QuoteEntity(
        id = page * AppModule.PAGE_SIZE + index,
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
    return data.mapIndexed { index, quoteDto ->
        quoteDto.toQuoteEntity(
            pagination.currentPage,
            index
        )
    }
}