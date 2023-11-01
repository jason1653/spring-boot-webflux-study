package com.example.part18.book.v7

data class BookResponse(
    val bookId: Long = 0,
    val titleKorean: String? = null,
    val titleEnglish: String? = null,
    val description: String? = null,
    val author: String? = null,
    val isbn: String? = null,
    val publishDate: String? = null,
) {
}