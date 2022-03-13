package com.truecaller.testassignment.data.network

import androidx.lifecycle.LiveData
import com.truecaller.testassignment.utils.network.ApiResponse
import retrofit2.http.POST

interface ContentApi {

    @POST("life-as-an-android-engineer")
    fun getContent(): LiveData<ApiResponse<String>>
}