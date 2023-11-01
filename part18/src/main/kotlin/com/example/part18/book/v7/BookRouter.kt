package com.example.part18.book.v7

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration("bookRouterV7")
class BookRouter {
    @Bean
    fun routeBookV7(handler: BookHandler): RouterFunction<ServerResponse> {
        return router {
            accept(MediaType.APPLICATION_JSON).nest {
                POST("/v7/books", handler::createBook)
                PATCH("/v7/books/{book-id}", handler::updateBook)
                GET("/v7/books", handler::getBooks)
                GET("/v7/boos/{book-id}", handler::getBook)
            }
        }
    }
}
