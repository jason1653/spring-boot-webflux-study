package com.example.part33.v1

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions.route
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration("bookRouterV1")
class BookRouter {
    @Bean
    fun routerBookV1(handler: BookHandler): RouterFunction<ServerResponse> {
        return route()
            .GET("/v1/books/{book-id}", handler::getBook)
            .GET("/v1/books", handler::getBooks)
            .POST("/v1/books", handler::createBook)
            .build()
    }
}