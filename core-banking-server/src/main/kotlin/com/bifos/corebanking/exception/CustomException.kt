package com.bifos.corebanking.exception

import com.fasterxml.jackson.annotation.JsonInclude

class CustomException(
    val statusCode: Int,
    val errorCode: Int,
    val errorMessage: String,
    val debugs: Map<String, Any>? = null
) : RuntimeException(errorMessage) {

}