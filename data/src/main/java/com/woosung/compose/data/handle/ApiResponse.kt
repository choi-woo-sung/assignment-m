package com.woosung.compose.data.handle

import kotlinx.serialization.Serializable
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException

@Serializable
data class ApiResponse<T>(
    val data: T,
)

sealed class NetworkResult<T> {
    data class Success<T>(val data: T) : NetworkResult<T>()

    // 네트워크 에러
    data class NetworkError<T>(val code: Int, val message: String?) : NetworkResult<T>()

    // 예외 발생
    data class Exception<T>(val e: Throwable) : NetworkResult<T>()
}


 suspend fun <T : Any> handleApi(
    execute: suspend () -> Response<ApiResponse<T>>
 ): NetworkResult<T> {
    return try {
        val response = execute()
        val body = response.body()?.data
        if (response.isSuccessful && body != null) {
            NetworkResult.Success(body)
        } else {
            NetworkResult.NetworkError(code = response.code(), message = response.message())
        }
    } catch (e: HttpException) {
        NetworkResult.NetworkError(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        NetworkResult.Exception(e)
    }
 }
