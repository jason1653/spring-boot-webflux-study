package com.example.part18.book.v8

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import reactor.util.function.Tuple2
import reactor.util.function.Tuples
import java.net.URI

@Component("BookHandlerV8")
class BookHandler(
    private val bookService: BookService,
    private val validator: BookValidator,
) {
    fun createBook(request: ServerRequest): Mono<ServerResponse> {
        return request
            .bodyToMono(BookPostDto::class.java)
            .doOnNext { post -> validator.validate(post) }
            .flatMap { post ->
                val book = Book()

                book.titleKorean = post.titleKorean
                book.titleEnglish = post.titleEnglish
                book.description = post.description
                book.author = post.author
                book.isbn = post.isbn
                book.publishDate = post.publishDate

                bookService.saveBook(book)
            }
            .flatMap { post ->
                ServerResponse
                    .created(URI.create("/v5/books/" + post.bookId)).build()
            }
    }

    fun updateBook(request: ServerRequest): Mono<ServerResponse> {
        val bookId = request.pathVariable("book-id").toLong()
        return request
            .bodyToMono(BookPatchDto::class.java)
            .doOnNext { patch -> validator.validate(patch) }
            .flatMap { patch ->
                patch.bookId = bookId
                return@flatMap bookService.updateBook(patch as Book)
            }
            .flatMap { book ->
                ServerResponse
                    .ok()
                    .bodyValue(book)
            }
    }

    fun getBook(request: ServerRequest): Mono<ServerResponse> {
        val bookId = request.pathVariable("book-id").toLong()
        return bookService.findBook(bookId)
            .flatMap { book ->
                ServerResponse
                    .ok()
                    .bodyValue(book)
            }
    }

    fun getBooks(request: ServerRequest): Mono<ServerResponse> {
        val pageable = pagePageAndSize(request)
        return bookService.findBooks(pageable.t1, pageable.t2)
            .flatMap { books ->
                ServerResponse
                    .ok()
                    .bodyValue(books)
            }
    }

    private fun pagePageAndSize(request: ServerRequest): Tuple2<Long, Long> {
        val page = request.queryParam("page").map(java.lang.Long::parseLong).orElse(0L)
        val size = request.queryParam("size").map(java.lang.Long::parseLong).orElse(0L)

        return Tuples.of(page, size)
    }
}
