package com.example.part31.filter

import org.springframework.web.reactive.function.server.HandlerFilterFunction
import org.springframework.web.reactive.function.server.HandlerFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

class BookRouterFunctionFilter : HandlerFilterFunction<ServerResponse?, ServerResponse?> {
    override fun filter(request: ServerRequest, next: HandlerFunction<ServerResponse?>): Mono<ServerResponse?> {
        val path: String = request.requestPath().value()

        return next.handle(request).doAfterTerminate {
            println(
                "path: " + path + ", status: " +
                        request.exchange().response.statusCode,
            )
        }
    }
}
