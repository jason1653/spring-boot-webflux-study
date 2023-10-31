package com.example.part18.book.v5

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration("bookRouterV5")
class BookRouter {
    @Bean
    fun routeBookV5(handler: BookHandler): RouterFunction<ServerResponse> {
        return router {
            accept(MediaType.APPLICATION_JSON).nest {
                POST("/v5/books", handler::createBook)
                PATCH("/v5/books/{book-id}", handler::updateBook)
                GET("/v5/books", handler::getBooks)
                GET("/v5/boos/{book-id}", handler::getBook)
            }
        }
    }
}
