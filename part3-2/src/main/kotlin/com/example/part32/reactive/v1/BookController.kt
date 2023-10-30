package com.example.part32.reactive.v1

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@RestController
@RequestMapping("/v1/books")
class BookController(
    val bookService: BookService,
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postBook(@RequestBody bookkDto: Mono<BookDto.Post>): Mono<Book> {
        val book: Mono<BookDto.Post> = bookService.createBook(bookkDto)

        val response: Mono<Book> = book.map {
            Book(
                bookId = 1L,
                titleKorean = it.titleKorean,
                titleEnglish = it.titleEnglish,
                description = it.description,
                author = it.author,
                isbn = it.isbn,
                publishDate = it.publishDate,
                createdAt = LocalDateTime.now(),
                modifiedAt = LocalDateTime.now(),
            )
        }

        return response
    }

    @PatchMapping("/{book-id}")
    fun patchBook(@PathVariable("book-id") bookId: Long, @RequestBody bookDto: Mono<BookDto.Patch>): Mono<Book> {
        val book: Mono<BookDto.Patch> = bookService.updateBook(bookDto)

        val response: Mono<Book> = book.map {
            Book(
                bookId = bookId,
                titleKorean = it.titleKorean,
                titleEnglish = it.titleEnglish,
                description = it.description,
                author = it.author,
                isbn = it.isbn,
                publishDate = it.publishDate,
                createdAt = LocalDateTime.now(),
                modifiedAt = LocalDateTime.now(),
            )
        }

        return response
    }

    @GetMapping("/{book-id}")
    fun getBook(@PathVariable("book-id") bookId: Long): Mono<Book> {
        val book = bookService.findBook(bookId)

        val bookResponse = book.map {
            Book(
                bookId = it.bookId,
                titleKorean = it.titleKorean,
                titleEnglish = it.titleEnglish,
                description = it.description,
                author = it.author,
                isbn = it.isbn,
                publishDate = it.publishDate,
                createdAt = it.createdAt,
                modifiedAt = it.modifiedAt,
            )
        }

        return bookResponse
    }
}
