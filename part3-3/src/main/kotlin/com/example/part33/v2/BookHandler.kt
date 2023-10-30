package com.example.part33.v2

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.Errors
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.server.ServerWebInputException
import reactor.core.publisher.Mono
import java.net.URI

@Component("bookHandlerV2")
class BookHandler(
    val validator: BookValidator,
) {

    fun createBook(request: ServerRequest): Mono<ServerResponse> {
        return request.bodyToMono(BookDto.Post::class.java)
            .doOnNext { validation(it) }
            .map { it }
            .flatMap {
                ServerResponse.created(URI.create("/v2/books/" + it.bookId))
                    .build()
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
        )

        return ServerResponse.ok().bodyValue(book).switchIfEmpty(ServerResponse.notFound().build())
    }

    fun getBooks(request: ServerRequest): Mono<ServerResponse> {
        val books: List<Book> = listOf(
            Book(
                bookId = 1L,
                titleKorean = "코틀린을 다루는 기술1",
                titleEnglish = "Kotlin in Action1",
                description = "코틀린을 다루는 기술1",
                author = "Dmitry Jemerov, Svetlana Isakova",
                isbn = "9788968482475",
                publishDate = "2019-08-01",
            ),
            Book(
                bookId = 2L,
                titleKorean = "코틀린을 다루는 기술2",
                titleEnglish = "Kotlin in Action2",
                description = "코틀린을 다루는 기술2",
                author = "Dmitry Jemerov, Svetlana Isakova",
                isbn = "9788968482475",
                publishDate = "2019-08-01",
            ),
        )

        return ServerResponse.ok().bodyValue(books)
    }

    private fun validation(post: BookDto.Post) {

        val errors: Errors = BeanPropertyBindingResult(post, BookDto.Post::class.java.name)
        println("validation")
        validator.validate(post, errors)
        if (errors.hasErrors()) {
            println(errors.allErrors.toString())
            throw ServerWebInputException(errors.toString())
        }
    }
}
