package com.kamalnayan.newsapp.data.entity.headlines


import com.kamalnayan.newsapp.data.base.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Source(
    val id: String?,
    val name: String?
):BaseModel()