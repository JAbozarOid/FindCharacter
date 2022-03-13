package com.truecaller.testassignment.util.network

import com.truecaller.testassignment.util.constants.ApiConstants.Companion.ERROR_TIME_OUT
import com.truecaller.testassignment.util.constants.ApiConstants.Companion.UNKNOWN_ERROR
import java.net.SocketTimeoutException


sealed class ApiResponse<T> {
    companion object {
        fun <T> handleServerError(error: Throwable): Failure<T> {
            var errorMessage = error.message
            errorMessage = when (error) {
                is SocketTimeoutException -> {
                    ERROR_TIME_OUT
                }

                else -> {
                    UNKNOWN_ERROR
                }
            }
            return Failure(errorMessage)
        }
    }

    class Loading<T> : ApiResponse<T>()
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Failure<T>(val error: String) : ApiResponse<T>()
    class Complete<T> : ApiResponse<T>()
}

