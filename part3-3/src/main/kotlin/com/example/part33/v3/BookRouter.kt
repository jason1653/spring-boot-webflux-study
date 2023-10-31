package com.example.part33.v3

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions.route
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration("bookRouterV3")
class BookRouter {

    @Bean
    fun routeBookV3(handler: BookHandler): RouterFunction<ServerResponse> {
        return route()
            .GET("/v3/books", handler::getBooks)
            .GET("/v3/books/{book-id}", handler::getBook)
            .POST("/v3/books", handler::createBook)
            .PATCH("/v3/books/{book-id}", handler::updateBook)
            .build()
    }

    @Bean
    fun springValidator(): LocalValidatorFactoryBean {
        return LocalValidatorFactoryBean()
    }
}