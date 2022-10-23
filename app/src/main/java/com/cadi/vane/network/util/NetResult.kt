package com.cadi.vane.network.util

import com.cadi.vane.BuildConfig

/**
 * Defines a network result usually created in the repository layer
 */
sealed class NetResult<out T, out E> {
    data class Success<out T>(val value: T) : NetResult<T, Nothing>()
    data class Failure<out E>(val reason: E, val debugCause: Throwable? = defaultCause()) :
        NetResult<Nothing, E>()

    fun getOrNull(): T? {
        return when (this) {
            is Failure -> null
            is Success -> value
        }
    }

    companion object {
        internal fun defaultCause(): Throwable? {
            return if (BuildConfig.DEBUG) {
                IllegalStateException("Result.Failure should not happen :)")
            } else {
                null
            }
        }
    }
}

/** Perform conditional operation on network success */
inline fun <T, E> NetResult<T, E>.doOnSuccess(block: (T) -> Unit): NetResult<T, E> {
    if (this is NetResult.Success) {
        block(this.value)
    }
    return this
}

/** Perform conditional operation on network failure */
inline fun <T, E> NetResult<T, E>.doOnFailure(block: (E) -> Unit): NetResult<T, E> {
    if (this is NetResult.Failure) {
        block(this.reason)
    }
    return this
}

/**
 * Get when both types are the same
 */
fun <T> NetResult<T, T>.getUnified(): T {
    return when (this) {
        is NetResult.Success -> this.value
        is NetResult.Failure -> this.reason
    }
}