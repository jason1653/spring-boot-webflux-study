package com.example.part18.book.v6

data class BookPatchDto(
    var bookId: Long? = null,

    private val titleKorean: String? = null,

    private val titleEnglish: String? = null,

    private val description: String? = null,

    private val author: String? = null,

    private val isbn: String? = null,

    private val publishDate: String? = null,
)