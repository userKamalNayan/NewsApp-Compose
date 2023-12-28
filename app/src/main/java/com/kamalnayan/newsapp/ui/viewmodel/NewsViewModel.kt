package com.kamalnayan.newsapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kamalnayan.newsapp.data.api.result.NetworkResult
import com.kamalnayan.newsapp.data.entity.headlines.HeadlineResponse
import com.kamalnayan.newsapp.ui.repository.NewsRepository
import com.kamalnayan.utilities.AppConstants
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/** @Author Kamal Nayan
Created on: 27/12/23
 **/
@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    init {
        getNewsHeadline(AppConstants.COUNTRY_US)
    }

    private val _newsHeadlineState: MutableStateFlow<NetworkResult<HeadlineResponse>> =
        MutableStateFlow(NetworkResult.Loading())
    val newsHeadlineState: StateFlow<NetworkResult<HeadlineResponse>> = _newsHeadlineState

    fun getNewsHeadline(country: String) {
        viewModelScope.launch {
            val response = newsRepository.getNewsHeadlines(country)
            response.onSuccess {
                _newsHeadlineState.tryEmit(NetworkResult.Success(this.data))
            }.onError {
                _newsHeadlineState.tryEmit(NetworkResult.Error("Some Error Occurred"))
            }.onFailure {
                _newsHeadlineState.tryEmit(NetworkResult.Error("Some Failure Occurred"))
            }
        }
    }
}