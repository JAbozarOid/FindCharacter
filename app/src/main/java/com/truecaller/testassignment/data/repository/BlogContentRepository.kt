package com.truecaller.testassignment.data.repository

import com.truecaller.testassignment.data.network.BlogContentService
import com.truecaller.testassignment.util.network.NetworkUtil.safeApiCall
import javax.inject.Inject

class BlogContentRepository @Inject constructor(
    private var blogContentService: BlogContentService
) {

    suspend fun getBlogContent() = safeApiCall {
        blogContentService.getBlogContentApi()
    }
}