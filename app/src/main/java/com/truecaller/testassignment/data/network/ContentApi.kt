package com.truecaller.testassignment.data.network

import androidx.lifecycle.LiveData
import com.truecaller.testassignment.utils.network.ApiResponse
import retrofit2.http.POST

interface ContentApi {

    @POST("life-as-an-android-engineer")
    fun findTenthCharacter(): LiveData<ApiResponse<String>>


    @POST("life-as-an-android-engineer")
    fun findEveryTenthCharacter(): LiveData<ApiResponse<String>>


    @POST("life-as-an-android-engineer")
    fun wordCounter(): LiveData<ApiResponse<String>>
}