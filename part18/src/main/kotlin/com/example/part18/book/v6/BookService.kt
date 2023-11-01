package com.example.part18.book.v6

import com.example.part18.exception.BusinessLogicException
import com.example.part18.exception.ExceptionCode
import com.example.part18.utils.CustomBeanUtils
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria.where
import org.springframework.data.relational.core.query.Query.query
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service("bookServiceV6")
class BookService(
    private val template: R2dbcEntityTemplate,
    private val beanUtils: CustomBeanUtils<Book>
) {

    fun saveBook(book: Book): Mono<Book> {
        return verifyExistIsbn(book.isbn)
            .then(template.insert(book))
    }

    fun updateBook(book: Book): Mono<Book> {
        return findVerifiedBook(book.bookId)
            .map { findBook -> beanUtils.copyNonNullProperties(book, findBook) }
            .flatMap { updatingBook ->
                template.update(updatingBook as Book)
            }
    }

    fun findBook(bookId: Long): Mono<Book> {
        return findVerifiedBook(bookId)
    }

    fun findBooks(): Mono<List<Book>> {
        return template.select(Book::class.java).all().collectList()
    }

    private fun verifyExistIsbn(isbn: String): Mono<Book> {
        return template.selectOne(query(where("ISBN").`is`(isbn)), Book::class.java)
            .flatMap {
                if (it != null) {
                    return@flatMap Mono.error(BusinessLogicException(ExceptionCode.BOOK_EXISTS))
                }
                return@flatMap Mono.empty()
            }


    }

    private fun findVerifiedBook(bookId: Long): Mono<Book> {
        return template.selectOne(query(where("BOOK_ID").`is`(bookId)), Book::class.java)
            .switchIfEmpty(Mono.error(BusinessLogicException(ExceptionCode.BOOK_NOT_FOUND)))
    }
}