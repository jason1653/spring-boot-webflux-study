package com.example.part18.book.v8

import jakarta.validation.constraints.NotBlank

data class BookPostDto(
    var bookId: Long? = null,

    @NotBlank val titleKorean: String,

    @NotBlank val titleEnglish: String,

    @NotBlank val description: String,

    @NotBlank val author: String,

    @NotBlank val isbn: String,

    @NotBlank val publishDate: String,
) {
}