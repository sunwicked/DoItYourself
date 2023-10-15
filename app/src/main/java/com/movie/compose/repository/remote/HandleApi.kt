package com.movie.compose.repository.remote

import retrofit2.HttpException
import retrofit2.Response
import com.movie.compose.repository.remote.ApiResult.Success
import com.movie.compose.repository.remote.ApiResult.Exception
import com.movie.compose.repository.remote.ApiResult.Error

//https://proandroiddev.com/modeling-retrofit-responses-with-sealed-classes-and-coroutines-9d6302077dfe
suspend fun <T : Any> handleApi(
    execute: suspend () -> Response<T>
): ApiResult<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            Success(body)
        } else {
            Error(code = response.code(), message = response.message())
        }
    } catch (e: HttpException) {
        Error(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        Exception(e)
    }
}