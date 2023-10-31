package com.example.part33.v3

import java.time.LocalDateTime

data class Book(
    val bookId: Long = 0,
    val titleKorean: String? = null,
    val titleEnglish: String? = null,
    val description: String? = null,
    val author: String? = null,
    val isbn: String? = null,
    val publishDate: String? = null,
    val createdAt: LocalDateTime? = null,
    val modifiedAt: LocalDateTime? = null
)
