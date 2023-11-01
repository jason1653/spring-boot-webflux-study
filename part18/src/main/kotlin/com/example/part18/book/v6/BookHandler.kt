package com.example.part18.book.v6

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.net.URI

@Component("BookHandlerV6")
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
                return@flatMap bookService.updateBook(patch as Book)7
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
        return bookService.findBooks()
            .flatMap { books ->
                ServerResponse
                    .ok()
                    .bodyValue(books)
            }
    }
}
