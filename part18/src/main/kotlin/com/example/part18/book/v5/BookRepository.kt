package com.example.part18.book.v5

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository("bookReposutoryV5")
interface BookRepository : ReactiveCrudRepository<Book, Long> {
    fun findByIsbn(isgn: String): Mono<Book>
}