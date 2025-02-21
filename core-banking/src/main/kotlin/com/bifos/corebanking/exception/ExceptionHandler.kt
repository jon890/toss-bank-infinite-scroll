package com.bifos.corebanking.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(value = [CustomException::class])
    fun handleCustomException(customException: CustomException): ResponseEntity<*> {

        return ResponseEntity.status(customException.statusCode)
            .body(
                ExceptionResponse(
                    code = customException.errorCode,
                    message = customException.errorMessage,
                    debugs = customException.debugs
                )
            )
    }
}