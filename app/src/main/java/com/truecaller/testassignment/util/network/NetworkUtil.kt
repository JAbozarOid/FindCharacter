package com.truecaller.testassignment.util.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

object NetworkUtil {

    suspend inline fun <T> safeApiCall(
        crossinline body: suspend () -> T
    ): ApiResponse<T> {
        return try {
            // blocking block
            val response = withContext(Dispatchers.IO) {
                body()
            }
            ApiResponse.Success(response)
        } catch (e: Exception) {
            ApiResponse.handleServerError(e)
        }
    }


    inline fun <T> flowResponse(crossinline body: suspend () -> ApiResponse<T>) =
        flow {
            emit(ApiResponse.Loading())
            val result = body()
            emit(result)
            emit(ApiResponse.Complete())
        }.flowOn(Dispatchers.IO)
}