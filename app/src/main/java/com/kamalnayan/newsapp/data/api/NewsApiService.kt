package com.kamalnayan.newsapp.data.api

import com.kamalnayan.newsapp.data.entity.headlines.HeadlineResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

/** @Author Kamal Nayan
Created on: 28/12/23
 **/
interface NewsApiService {

    @GET("v2/top-headlines")
    suspend fun getNewsHeadline(
        @Query("country") country: String
    ): ApiResponse<HeadlineResponse>
}