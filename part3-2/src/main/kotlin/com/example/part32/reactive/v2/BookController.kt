package com.example.part32.reactive.v2

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController("bookControllerV2")
@RequestMapping("/v2/books")
class BookController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postBook() {

    }

    @PatchMapping("/{book-id}")
    fun patchBook() {

    }

    @GetMapping("/{book-id}")
    fun getBook() {

    }
}