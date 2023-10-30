package com.example.part31.filter

import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

class BookLogFilter : WebFilter {
    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val path = exchange.request.uri.getPath()
        return chain.filter(exchange).doAfterTerminate {
            if (path.contains("books")) {
                System.out.println("path: " + path + ", status: " + exchange.response.statusCode)
            }
        }
    }
}