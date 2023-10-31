package com.example.part33.v3

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.net.URI
import java.time.LocalDateTime

@Component("bookHandlerV3")
class BookHandler(
    private val bookValidator: BookValidator,
) {

    fun createBook(request: ServerRequest): Mono<ServerResponse> {
        return request.bodyToMono(BookDto.Post::class.java)
            .doOnNext { bookValidator.validate(it) }
            .map { it }
            .flatMap { ServerResponse.created(URI.create("/v3/books/" + it.bookId)).build() }
    }

    fun updateBook(request: ServerRequest): Mono<ServerResponse> {
        val bookId = request.pathVariable("book-id").toLong()
        return request
            .bodyToMono(BookDto.Patch::class.java)
            .doOnNext { bookValidator.validate(it) }
            .map { it }
            .flatMap {
                ServerResponse.ok().bodyValue(it)
            }
    }

    fun getBook(request: ServerRequest): Mono<ServerResponse> {
        val bookId = request.pathVariable("book-id").toLong()
        val book = Book(
            bookId = bookId,
            titleKorean = "코틀린을 다루는 기술",
            titleEnglish = "Kotlin in Action",
            description = "코틀린을 다루는 기술",
            author = "Dmitry Jemerov, Svetlana Isakova",
            isbn = "9788968482475",
            publishDate = "2019-08-01",
            createdAt = LocalDateTime.now(),
            modifiedAt = LocalDateTime.now(),
        )

        return ServerResponse
            .ok()
            .bodyValue(book)
            .switchIfEmpty(ServerResponse.notFound().build())
    }

    fun getBooks(requets: ServerRequest): Mono<ServerResponse> {
        val book: List<Book> = listOf(
            Book(
                bookId = 1L,
                titleKorean = "코틀린을 다루는 기술1",
                titleEnglish = "Kotlin in Action1",
                description = "코틀린을 다루는 기술1",
                author = "test",
                isbn = "9788968482475",
                publishDate = "2019-08-01",
                createdAt = LocalDateTime.now(),
                modifiedAt = LocalDateTime.now(),
            ),
            Book(
                bookId = 2L,
                titleKorean = "코틀린을 다루는 기술2",
                titleEnglish = "Kotlin in Action2",
                description = "코틀린을 다루는 기술2",
                author = "test",
                isbn = "9788968482475",
                publishDate = "2019-08-01",
                createdAt = LocalDateTime.now(),
                modifiedAt = LocalDateTime.now(),
            )
        )

        return ServerResponse.ok().bodyValue(book)
    }
}
