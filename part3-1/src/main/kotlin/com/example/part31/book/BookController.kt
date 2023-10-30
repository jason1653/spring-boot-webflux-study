package com.example.part31.book

import com.example.part31.dto.BookDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/v1/controller/books")
class BookController {
    @GetMapping("/{book-id}")
    fun getBook(@PathVariable("book-id") bookId: Long): Mono<BookDto> {
        val bookDto = BookDto(
            bookId = bookId,
            bookName = "Kotlin in Action",
            author = "Jason",
            isbn = "123-45-6789-111-1",
        )
        return Mono.just(bookDto)
    }
}
