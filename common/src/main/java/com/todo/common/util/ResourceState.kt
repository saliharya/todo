package com.todo.common.util

import com.google.gson.annotations.SerializedName
import com.todo.common.util.Constant

sealed class ResourceState<T> {
    data class Loading<T>(val data: T? = null) : ResourceState<T>()
    data class Success<T>(val data: T?) : ResourceState<T>()
    data class Error<T>(
        @SerializedName("message") val message: String? = Constant.SOMETHING_WENT_WRONG,
        val data: T? = null,
    ) : ResourceState<T>()
}