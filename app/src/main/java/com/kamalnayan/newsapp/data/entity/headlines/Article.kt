package com.kamalnayan.newsapp.data.entity.headlines


import com.kamalnayan.newsapp.data.base.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
): BaseModel()