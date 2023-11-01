package com.example.part18.book.v7

import org.springframework.data.domain.Pageable
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Repository("bookRepositoryV7")
interface BookRepository : ReactiveCrudRepository<Book, Long> {
    fun findByIsbn(isbn: String): Mono<Book>
    fun findAllBy(pageable: Pageable): Flux<Book>


}