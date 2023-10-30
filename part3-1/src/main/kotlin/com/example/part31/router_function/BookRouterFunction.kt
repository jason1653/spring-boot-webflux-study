package com.example.part31.router_function

import com.example.part31.dto.BookDto
import com.example.part31.filter.BookRouterFunctionFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.result.method.annotation.ResponseBodyResultHandler
import reactor.core.publisher.Mono

@Configuration
class BookRouterFunction(private val body: ResponseBodyResultHandler) {
    @Bean
    fun routerFunction(): RouterFunction<*> {
        return RouterFunctions
            .route(
                RequestPredicates.GET("/v1/controller/books/{book-id}"),
            ) { request: ServerRequest? -> request!!.let { this.getBook(it) } }
            .filter(BookRouterFunctionFilter())
    }

    fun getBook(request: ServerRequest): Mono<ServerResponse> {
        val bookDto = BookDto(
            bookId = request.pathVariable("book-id").toLong(),
            bookName = "Kotlin in Action",
            author = "Jason",
            isbn = "123-45-6789-111-1",
        )
        return ServerResponse
            .ok()
            .body(
                Mono.just(
                    bookDto,
                ),
                BookDto::class.java,
            )
    }
}
