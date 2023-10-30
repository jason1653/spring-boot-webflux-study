package com.example.part33.v2

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions.route
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration("bookRouterV2")
class BookRouter {
    @Bean
    fun routerBookV2(handler: BookHandler): RouterFunction<ServerResponse> {
        return router {
            accept(MediaType.APPLICATION_JSON).nest {
                GET("/v2/books/{book-id}", handler::getBook)
                GET("/v2/books", handler::getBooks)
                POST("/v2/books", handler::createBook)
            }
        }
    }
}
