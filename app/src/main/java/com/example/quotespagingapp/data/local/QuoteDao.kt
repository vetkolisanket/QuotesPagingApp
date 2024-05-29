package com.example.quotespagingapp.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface QuoteDao {

    @Upsert
    suspend fun upsertAll(quotes: List<QuoteEntity>)

    @Query("SELECT * FROM quoteentity")
    fun pagingSource(): PagingSource<String, QuoteEntity>

    @Query("DELETE FROM quoteentity")
    suspend fun clearAll()

}