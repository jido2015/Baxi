package com.olajide.capricon

sealed class Resource<T>(val data: T? = null, val message: String? =null) {
    class Success<T>(data: T) : Resource<T>(data, null)
    class Failure<T>(message: String) : Resource<T>(null, message)
    class Exception<T>(message: String?) : Resource<T>(null, message)
}