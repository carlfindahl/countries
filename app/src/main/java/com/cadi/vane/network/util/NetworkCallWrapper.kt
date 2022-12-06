package com.cadi.vane.network.util

import retrofit2.HttpException
import java.io.IOException

inline fun <T> doNetwork(
    block: () -> T
): NetResult<T, String> {
    return try {
        val value = block()
        NetResult.Success(value)
    } catch (e: HttpException) {
        NetResult.Failure("Network Error ${e.code()}.")
    } catch (e: IOException) {
        NetResult.Failure("IO Disruption.")
    } catch (e: KotlinNullPointerException) {
        NetResult.Failure("Data unavailable.")
    } catch (e: OutOfMemoryError) {
        NetResult.Failure("Out of memory.")
    }
}
