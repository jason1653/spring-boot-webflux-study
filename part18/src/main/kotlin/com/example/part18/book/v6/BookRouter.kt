package com.example.part18.book.v6

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration("bookRouterV6")
class BookRouter {
    @Bean
    fun routeBookV6(handler: BookHandler): RouterFunction<ServerResponse> {
        return router {
            accept(MediaType.APPLICATION_JSON).nest {
                POST("/v6/books", handler::createBook)
                PATCH("/v6/books/{book-id}", handler::updateBook)
                GET("/v6/books", handler::getBooks)
                GET("/v6/boos/{book-id}", handler::getBook)
            }
        }
    }
}
