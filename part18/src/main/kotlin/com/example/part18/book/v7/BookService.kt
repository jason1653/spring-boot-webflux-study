package com.example.part18.book.v7

import com.example.part18.exception.BusinessLogicException
import com.example.part18.exception.ExceptionCode
import com.example.part18.utils.CustomBeanUtils
import jakarta.validation.constraints.Positive
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service("bookServiceV7")
class BookService(
    private val bookRepository: BookRepository,
    private val beanUtils: CustomBeanUtils<Book>
) {

    fun saveBook(book: Book): Mono<Book> {
        return verifyExistIsbn(book.isbn)
            .then(bookRepository.save(book))
    }

    fun updateBook(book: Book): Mono<Book> {
        return findVerifiedBook(book.bookId)
            .map { findBook -> beanUtils.copyNonNullProperties(book, findBook) }
            .flatMap { updatingBook ->
                bookRepository.save(updatingBook as Book)
            }
    }

    fun findBook(bookId: Long): Mono<Book> {
        return findVerifiedBook(bookId)
    }

    fun findBooks(@Positive page: Int, @Positive size: Int): Mono<MutableList<Book>> {
        val pageable: Pageable = PageRequest.of(page - 1, size, Sort.by("bookId").descending())
        return bookRepository.findAllBy(pageable).collectList()
    }


    private fun verifyExistIsbn(isbn: String): Mono<Book> {
        return bookRepository.findByIsbn(isbn)
            .flatMap {
                if (it != null) {
                    return@flatMap Mono.error(BusinessLogicException(ExceptionCode.BOOK_EXISTS))
                }
                return@flatMap Mono.empty()
            }
    }

    private fun findVerifiedBook(bookId: Long): Mono<Book> {
        return bookRepository.findById(bookId)
            .switchIfEmpty(Mono.error(BusinessLogicException(ExceptionCode.BOOK_NOT_FOUND)))
    }
}