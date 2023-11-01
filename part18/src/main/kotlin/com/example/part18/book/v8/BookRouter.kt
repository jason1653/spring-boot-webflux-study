package com.example.part18.book.v8

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration("bookRouterV8")
class BookRouter {
    @Bean
    fun routeBookV8(handler: BookHandler): RouterFunction<ServerResponse> {
        return router {
            accept(MediaType.APPLICATION_JSON).nest {
                POST("/v8/books", handler::createBook)
                PATCH("/v8/books/{book-id}", handler::updateBook)
                GET("/v8/books", handler::getBooks)
                GET("/v8/boos/{book-id}", handler::getBook)
            }
        }
    }
}
