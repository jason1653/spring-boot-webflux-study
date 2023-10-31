package com.example.part18.exception

enum class ExceptionCode(private val status: Int, private val message: String) {
    BOOK_NOT_FOUND(404, "Book not found"),
    BOOK_EXISTS(409, "Book exists"),
}
