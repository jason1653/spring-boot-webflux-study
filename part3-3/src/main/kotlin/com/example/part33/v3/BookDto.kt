package com.example.part33.v3

import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime

class BookDto {

    class Post {
        var bookId: Long? = null

        @NotBlank
        private val titleKorean: String? = null

        @NotBlank
        private val titleEnglish: String? = null

        @NotBlank
        private val description: String? = null

        @NotBlank
        private val author: String? = null

        @NotBlank
        private val isbn: String? = null

        @NotBlank
        private val publishDate: String? = null
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
        val modifiedAt: LocalDateTime? = null,

    )
}
