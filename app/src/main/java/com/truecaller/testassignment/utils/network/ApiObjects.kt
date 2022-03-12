package com.truecaller.testassignment.utils.network

import android.util.Log
import androidx.lifecycle.LiveData
import com.hadilq.liveevent.LiveEvent
import com.truecaller.testassignment.utils.constants.ApiConstants.Companion.ERROR_GENERAL_ERROR
import com.truecaller.testassignment.utils.constants.ApiConstants.Companion.ERROR_NO_NETWORK

object ApiObjects {

    private const val TAG = "ApiObjects"

    fun <T> LiveEvent<ApiResponse<T>>.requestInTryCatch(action: (LiveEvent<ApiResponse<T>>) -> Unit) {
        try {
            action(this)
        } catch (e: Exception) {
            Log.e(TAG, "error: ", e)
            this.value = ApiResponse.Error(ERROR_GENERAL_ERROR)
        }
    }


    fun <T> doIfConnectedResponse(
        networkListener: NetworkListener,
        func: () -> Unit
    ): ApiResponse<T> {
        return if (networkListener.isConnected) {
            func()
            ApiResponse.Loading()
        } else {
            ApiResponse.ErrorTryAgain(ERROR_NO_NETWORK)
        }
    }

    fun <T> LiveEvent<T>.addSourceGeneric(response: LiveData<T>) {
        this.addSource(response) {
            this.removeSource(response)
            this.value = it as T
        }
    }
}