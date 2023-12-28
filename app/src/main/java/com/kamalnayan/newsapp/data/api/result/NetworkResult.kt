package com.kamalnayan.newsapp.data.api.result

/** @Author Kamal Nayan
Created on: 28/12/23
 **/
sealed class NetworkResult<out SomeResult> {

    data class Success<SomeResult>(val data: SomeResult) : NetworkResult<SomeResult>()
    class Loading<SomeResult> : NetworkResult<SomeResult>()
    data class Error<SomeResult>(val error: String) : NetworkResult<SomeResult>()
}