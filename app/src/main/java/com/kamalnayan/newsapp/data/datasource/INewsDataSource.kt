package com.kamalnayan.newsapp.data.datasource

import com.kamalnayan.newsapp.data.entity.headlines.HeadlineResponse
import com.skydoves.sandwich.ApiResponse

/** @Author Kamal Nayan
Created on: 28/12/23
 **/
interface INewsDataSource {
    suspend fun getNewsHeadline(country: String): ApiResponse<HeadlineResponse>
}