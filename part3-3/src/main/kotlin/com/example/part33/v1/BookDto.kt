package com.example.part33.v1

import java.time.LocalDateTime


class BookDto {
    class Post {
        val bookId: Long? = null
        val titleKorean: String? = null
        val titleEnglish: String? = null
        val description: String? = null
        val author: String? = null
        val isbn: String? = null
        val publishDate: String? = null
    }

    class Patch {
        val bookId: Long = 0
        val titleKorean: String? = null
        val titleEnglish: String? = null
        val description: String? = null
        val author: String? = null
        val isbn: String? = null
        val publishDate: String? = null
    }

    data class Response(
        val bookId: Long = 0,
        val titleKorean: String? = null,
        val titleEnglish: String? = null,
        val description: String? = null,
        val author: String? = null,
        val isbn: String? = null,
        val publishDate: String? = null,
        val createdAt: LocalDateTime? = null,
        val modifiedAt: LocalDateTime? = null

    ) {
    }
}