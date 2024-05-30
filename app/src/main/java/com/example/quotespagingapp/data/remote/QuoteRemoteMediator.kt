package com.example.quotespagingapp.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.quotespagingapp.data.local.QuoteDatabase
import com.example.quotespagingapp.data.local.QuoteEntity
import com.example.quotespagingapp.data.mapper.toQuoteEntities
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class QuoteRemoteMediator(
    private val quoteDb: QuoteDatabase,
    private val quoteApi: QuoteApi
): RemoteMediator<Int, QuoteEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, QuoteEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        lastItem.page + 1
                    }
                }
            }

            val quotesResponse = quoteApi.getQuotes(
                page = loadKey,
                limit = state.config.pageSize
            )

            quoteDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    quoteDb.dao.clearAll()
                }
                val quoteEntities = quotesResponse.toQuoteEntities()
                quoteDb.dao.upsertAll(quoteEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = quotesResponse.data.size < state.config.pageSize
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }


}