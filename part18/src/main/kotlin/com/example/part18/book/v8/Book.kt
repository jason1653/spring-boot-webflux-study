package com.example.part18.book.v8

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import java.time.LocalDateTime

class Book {
    @Id
    var bookId: Long = 0L
    var titleKorean: String = ""
    var titleEnglish: String = ""
    var description: String = ""
    var author: String = ""
    var isbn: String = ""
    var publishDate: String = ""

    @CreatedDate
    var createdAt: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    @Column("last_modified_at")
    var modifiedAt: LocalDateTime = LocalDateTime.now()
}
