package com.example.quotespagingapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.quotespagingapp.data.local.QuoteEntity
import com.example.quotespagingapp.data.mapper.toQuote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    pager: Pager<Int, QuoteEntity>
) : ViewModel() {

    val quotesPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toQuote() }
        }
        .cachedIn(viewModelScope)


}