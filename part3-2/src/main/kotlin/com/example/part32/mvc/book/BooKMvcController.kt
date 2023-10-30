package com.example.part32.mvc.book

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/mvc/books")
class BooKMvcController(
    val bookMvcService: BookMvcService,
) {

    @PostMapping
    fun postBook(@RequestBody bookPostDto: BooKPostDto): ResponseEntity<BooKPostDto> {
        val book = bookMvcService.createBook(bookPostDto)
        return ResponseEntity.ok(book)

    }

    @PatchMapping("/{book-id}")
    fun patchBook(@PathVariable("book-id") bookId: Long, @RequestBody bookPatchDto: BookPatchDto): ResponseEntity<BookPatchDto> {
        val book = bookMvcService.updateBook(bookPatchDto)
        return ResponseEntity.ok(book)
    }

    @GetMapping("/{book-id}")
    fun getBook(@PathVariable("book-id") bookId: Long): ResponseEntity<Book> {
        val book = bookMvcService.findBook(bookId)
        return ResponseEntity.ok(book)
    }
}