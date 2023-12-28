package com.kamalnayan.newsapp.ui.repository

import com.kamalnayan.newsapp.data.datasource.INewsDataSource
import com.kamalnayan.newsapp.data.entity.headlines.HeadlineResponse
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/** @Author Kamal Nayan
Created on: 27/12/23
 **/

@Singleton
class NewsRepository @Inject constructor(private val newsDataSource: INewsDataSource) {

    suspend fun getNewsHeadlines(country: String): ApiResponse<HeadlineResponse> {
        return withContext(Dispatchers.IO) { newsDataSource.getNewsHeadline(country) }
    }
}