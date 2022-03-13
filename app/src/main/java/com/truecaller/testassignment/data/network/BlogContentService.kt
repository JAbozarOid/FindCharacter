package com.truecaller.testassignment.data.network

import retrofit2.http.GET

interface BlogContentService {

    @GET("life-as-an-android-engineer")
    suspend fun getBlogContentApi(): String
}