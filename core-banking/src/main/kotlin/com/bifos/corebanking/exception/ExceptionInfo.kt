package com.bifos.corebanking.exception

import org.springframework.http.HttpStatus

enum class ExceptionInfo(
    val status: HttpStatus,
    val customErrorCode: CustomErrorCode,
    val message: String
) {
    NOT_FOUND_ACCOUNT(
        HttpStatus.BAD_REQUEST,
        CustomErrorCode.INTERNAL_SERVER_WARNING,
        "계정 정보가 존재하지 않습니다"
    ),
    ;

    fun exception(): CustomException {
        return CustomException(
            statusCode = status.value(),
            errorCode = customErrorCode.code,
            errorMessage = message
        )
    }
}