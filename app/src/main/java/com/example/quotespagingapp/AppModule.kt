package com.example.quotespagingapp

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.quotespagingapp.data.local.QuoteDatabase
import com.example.quotespagingapp.data.local.QuoteEntity
import com.example.quotespagingapp.data.remote.QuoteApi
import com.example.quotespagingapp.data.remote.QuoteRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    const val PAGE_SIZE = 20

    @Provides
    @Singleton
    fun provideQuoteDatabase(@ApplicationContext context: Context): QuoteDatabase {
        return Room
            .databaseBuilder(
                context,
                QuoteDatabase::class.java,
                "quotes.db"
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideQuoteApi() = Retrofit.Builder()
        .baseUrl(QuoteApi.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create<QuoteApi>()

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideQuotePager(quoteDb: QuoteDatabase, quoteApi: QuoteApi): Pager<Int, QuoteEntity> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            remoteMediator = QuoteRemoteMediator(quoteDb, quoteApi),
            pagingSourceFactory = {
                quoteDb.dao.pagingSource()
            }
        )
    }

}