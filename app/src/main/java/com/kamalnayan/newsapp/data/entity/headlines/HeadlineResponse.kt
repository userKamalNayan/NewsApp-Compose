package com.kamalnayan.newsapp.data.entity.headlines


import com.kamalnayan.newsapp.data.base.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeadlineResponse(
    val articles: List<Article>,
    val status: String?,
    val totalResults: Int?
): BaseModel()