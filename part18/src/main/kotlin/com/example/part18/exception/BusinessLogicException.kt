package com.example.part18.exception


class BusinessLogicException(exceptionCode: ExceptionCode) : RuntimeException() {
    private val exceptionCode: ExceptionCode

    init {
        this.exceptionCode = exceptionCode
    }
}