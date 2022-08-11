package com.olajide.capricon.base

/**Sealed class for Handling Api Response*/
sealed class NetworkResult<T>(
    val data: T? = null,
    val errorText: String? = null
) {

    class Success<T>(data: T) : NetworkResult<T>(data)
    class Failure<T>(errorText: String?, data: T? = null) : NetworkResult<T>(data, errorText)
    class Loading<T> : NetworkResult<T>()
    class Empty<T> : NetworkResult<T>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[verifyCustomerData=$data]"
            is Failure -> "Error[Error=$errorText]"
            is Empty -> "Empty--"
            is Loading -> "Loading----"
        }
    }
}