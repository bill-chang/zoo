package Data

sealed class CallBackResource<T> {
    class Success<T>(val data: T) : CallBackResource<T>()
    class Error<T>(val errorMessage: String) : CallBackResource<T>()
    class Timeout<T>(val timeoutMessage: String? = "timeout") : CallBackResource<T>()
    class Exception<T>(val exceptionMessage: String? = "exception") : CallBackResource<T>()
    class HttpException<T>(val httpExceptionMessage: String? = "httpException") : CallBackResource<T>()
}