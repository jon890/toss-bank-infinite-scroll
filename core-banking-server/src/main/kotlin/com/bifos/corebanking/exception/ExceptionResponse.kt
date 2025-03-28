package com.bifos.corebanking.exception

import com.fasterxml.jackson.annotation.JsonInclude

data class ExceptionResponse(
    val code: Int,
    val message: String,
    @get:JsonInclude(JsonInclude.Include.NON_NULL)
    val debugs: Map<String, Any>? = null
) {
}