package com.bifos.corebanking.exception

import com.fasterxml.jackson.annotation.JsonInclude

class CustomException(
    val statusCode: Int,
    val errorCode: Int,
    val errorMessage: String,
    @get:JsonInclude(JsonInclude.Include.NON_NULL)
    val debugs: Map<String, Any>? = null
) : RuntimeException() {

}