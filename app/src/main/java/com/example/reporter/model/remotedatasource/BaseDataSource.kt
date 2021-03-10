package com.example.reporter.model.remotedatasource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception
import com.example.reporter.utils.Result

abstract class BaseDataSource {

    suspend fun <T>safeApiRequest(call : suspend () -> Response<T>) : Result<T> {

        return withContext(Dispatchers.IO) {
            try {
                val response = call.invoke()
                if(response.isSuccessful && response.body() != null) {
                    Result.Success(response.body()!!)
                }else{
                    when(response.code()) {
                        200 -> Result.Error("Empty success")
                        400 -> Result.Error("Please try after some time")
                        401 -> Result.Error("Please authorize yourself")
                        500 -> Result.Error("Something went wrong on the server side")
                        else -> Result.Error("Something went wrong")
                    }
                }
            }catch (e: Exception) {
                Result.Error("Please check your internet connection")
            }
        }

    }
}