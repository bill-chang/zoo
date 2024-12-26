package Data

import retrofit2.HttpException
import retrofit2.Response
import java.util.concurrent.TimeoutException

interface HandleApi {
    suspend fun <T : Any> apiHandle(
        execute: suspend () -> Response<T>,
    ): CallBackResource<T> {
        return try {
            val response = execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                CallBackResource.Success(body)
            } else {
                CallBackResource.Error(response.message())
            }
        } catch (e: HttpException) {
            CallBackResource.HttpException(e.message ?: e.toString())
        } catch (e: Exception) {
            CallBackResource.Exception(e.message ?: e.toString())
        } catch (e: TimeoutException) {
            CallBackResource.Timeout(e.message ?: e.toString())
        }
    }
}