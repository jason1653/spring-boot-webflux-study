package com.example.part32.mvc.book

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class BookMvcService {
    fun createBook(book: BooKPostDto): BooKPostDto {
        return book
    }

    fun updateBook(book: BookPatchDto): BookPatchDto {
        return book
    }

    fun findBook(bookId: Long): Book {
        return Book(
            bookId = bookId,
            titleKorean = "책 제목",
            titleEnglish = "Book Title",
            description = "책 설명",
            author = "책 저자",
            isbn = "1234567890",
            publishDate = "2022-03-22",
            createdAt = LocalDateTime.now(),
            modifiedAt = LocalDateTime.now(),
        )
    }
}
