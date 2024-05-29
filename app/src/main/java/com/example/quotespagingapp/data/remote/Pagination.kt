package com.example.quotespagingapp.data.remote

data class Pagination(
    val currentPage: Int,
    val nextPage: Int,
    val totalPages: Int
)