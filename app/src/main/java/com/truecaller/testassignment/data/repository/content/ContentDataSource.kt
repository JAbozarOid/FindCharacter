package com.truecaller.testassignment.data.repository.content

import androidx.lifecycle.LiveData
import com.truecaller.testassignment.data.network.ContentApi
import com.truecaller.testassignment.utils.network.ApiResponse
import javax.inject.Inject

class ContentDataSource @Inject constructor(private var contentApi: ContentApi) {

    fun findTenthCharacter(): LiveData<ApiResponse<Unit>> {
        return contentApi.findTenthCharacter()
    }

    fun findEveryTenthCharacter(): LiveData<ApiResponse<Unit>> {
        return contentApi.findEveryTenthCharacter()
    }

    fun wordCounter(): LiveData<ApiResponse<Unit>> {
        return contentApi.wordCounter()
    }
}