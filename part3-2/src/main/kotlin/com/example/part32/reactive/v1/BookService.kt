package com.example.part32.reactive.v1

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@Service
class BookService {
    fun createBook(book: BookDto.Post): Mono<BookDto.Post> {
        return Mono.just(book)
    }

    fun updateBook(book: BookDto.Patch): Mono<BookDto.Patch> {
        return Mono.just(book)
    }

    fun findBook(bookId: Long): Mono<BookDto.Response> {
        return Mono.just(
            BookDto.Response(
                bookId = bookId,
                titleKorean = "책 제목",
                titleEnglish = "Book Title",
                description = "책 설명",
                author = "책 저자",
                isbn = "1234567890",
                publishDate = "2022-03-22",
                createdAt = LocalDateTime.now(),
                modifiedAt = LocalDateTime.now(),
            ),
        )
    }
}
