package com.example.part18.book.v5

import org.mapstruct.Mapper
import org.mapstruct.MappingConstants

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, implementationName = "bookMapperV5")
interface BookMapper {
    fun bookPostToBook(bookPostDto: BookPostDto): Book
    fun bookPatchToBook(bookPatchDto: BookPatchDto): Book

    fun bookToResponse(book: Book): BookResponse

    fun booksToResponse(books: List<Book>): List<BookResponse>
}
