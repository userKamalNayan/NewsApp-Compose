package com.kamalnayan.newsapp.data.datasource.impl

import com.kamalnayan.newsapp.data.api.NewsApiService
import com.kamalnayan.newsapp.data.datasource.INewsDataSource
import com.kamalnayan.newsapp.data.entity.headlines.HeadlineResponse
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

/** @Author Kamal Nayan
Created on: 28/12/23
 **/
class NewsDataSourceImpl @Inject constructor(private val newsApiService: NewsApiService) :
    INewsDataSource {
    override suspend fun getNewsHeadline(country: String): ApiResponse<HeadlineResponse> {
        return newsApiService.getNewsHeadline(country)
    }
}