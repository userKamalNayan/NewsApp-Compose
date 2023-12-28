package com.kamalnayan.newsapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kamalnayan.newsapp.data.api.result.NetworkResult
import com.kamalnayan.newsapp.data.entity.headlines.HeadlineResponse
import com.kamalnayan.newsapp.ui.components.EmptyStateView
import com.kamalnayan.newsapp.ui.components.ErrorView
import com.kamalnayan.newsapp.ui.components.Loader
import com.kamalnayan.newsapp.ui.components.NewsList
import com.kamalnayan.newsapp.ui.components.NewsRowComponent
import com.kamalnayan.newsapp.ui.viewmodel.NewsViewModel

/** @Author Kamal Nayan
Created on: 27/12/23
 **/

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(newsViewModel: NewsViewModel = hiltViewModel()) {

    val newsHeadlineState by newsViewModel.newsHeadlineState.collectAsState()
    val pagerState = rememberPagerState(initialPage = 0, initialPageOffsetFraction = 0f) {
        when (newsHeadlineState) {
            is NetworkResult.Error -> 1
            is NetworkResult.Loading -> 1
            is NetworkResult.Success -> (newsHeadlineState as? NetworkResult.Success)?.data?.articles?.size
                ?: 0
        }
    }

    VerticalPager(
        state = pagerState, modifier = Modifier.fillMaxSize(), pageSize = PageSize.Fill,
        pageSpacing = 8.dp
    ) { page ->

        when (newsHeadlineState) {
            is NetworkResult.Loading -> {
                Loader()
            }

            is NetworkResult.Error -> {
                ErrorView(errorMessage = (newsHeadlineState as? NetworkResult.Error)?.error)
            }

            is NetworkResult.Success -> {
                val response = newsHeadlineState as NetworkResult.Success
                if (response.data.articles.isNotEmpty())
                    NewsRowComponent(page, response.data.articles[page])
                else
                    EmptyStateView()
            }
        }
    }
}

fun buildErrorView(error: NetworkResult.Error<HeadlineResponse>) {

}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}