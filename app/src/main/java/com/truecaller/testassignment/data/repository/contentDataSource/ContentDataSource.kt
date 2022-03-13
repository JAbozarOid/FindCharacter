package com.truecaller.testassignment.data.repository.contentDataSource

import androidx.lifecycle.LiveData
import com.truecaller.testassignment.data.network.ContentApi
import com.truecaller.testassignment.utils.network.ApiResponse
import javax.inject.Inject

class ContentDataSource @Inject constructor(private var contentApi: ContentApi) {

    fun getContent(): LiveData<ApiResponse<String>> {
        return contentApi.getContent()
    }
}