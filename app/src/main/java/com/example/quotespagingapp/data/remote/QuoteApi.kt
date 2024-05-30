package com.example.quotespagingapp.data.remote

import com.example.quotespagingapp.data.remote.model.QuotesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteApi {

    @GET("quotes")
    suspend fun getQuotes(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): QuotesResponse

    companion object {
        const val BASE_URL = "https://quote-garden.onrender.com/api/v3/"
    }


}