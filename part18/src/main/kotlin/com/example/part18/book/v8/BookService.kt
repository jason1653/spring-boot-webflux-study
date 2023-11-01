package com.example.part18.book.v8

import com.example.part18.exception.BusinessLogicException
import com.example.part18.exception.ExceptionCode
import com.example.part18.utils.CustomBeanUtils
import jakarta.validation.constraints.Positive
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria.where
import org.springframework.data.relational.core.query.Query.query
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.util.function.Tuple2
import reactor.util.function.Tuples

@Service("bookServiceV8")
class BookService(
    private val template: R2dbcEntityTemplate,
    private val beanUtils: CustomBeanUtils<Book>,
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

    fun findBooks(@Positive page: Long, @Positive size: Long): Mono<List<Book>> {
        return template
            .select(Book::class.java)
            .count()
            .flatMap { total ->
                val skipAndTask = getSkipAndTake(total, page, size)
                return@flatMap template
                    .select(Book::class.java)
                    .all()
                    .skip(skipAndTask.t1)
                    .take(skipAndTask.t2)
                    .collectSortedList { o1, o2 ->
                        (o1.bookId - o2.bookId).toInt()
                    }
            }
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

    private fun getSkipAndTake(total: Long, movePage: Long, size: Long): Tuple2<Long, Long> {
        val totalPages: Long = Math.ceil((total / size).toDouble()).toLong()
        val page: Long = if (movePage > totalPages) totalPages else movePage

        val skip: Long = if (total - (page - 1 * size) < 0) 0 else total - (page * size)
        val take: Long = if (total - (page - 1 * size) < 0) total - ((page - 1) * size) else size

        return Tuples.of(skip, take)
    }
}
