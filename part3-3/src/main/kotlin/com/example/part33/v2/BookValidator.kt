package com.example.part33.v2

import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator

@Component("bookValidatorV2")
class BookValidator : Validator {
    override fun supports(clazz: Class<*>): Boolean {
        return BookDto.Post::class.java.isAssignableFrom(clazz)
    }

    override fun validate(target: Any, errors: Errors) {
        val post: BookDto.Post = target as BookDto.Post

        ValidationUtils.rejectIfEmptyOrWhitespace(
            errors,
            "titleKorean",
            "titleKorean.empty",
        )

        ValidationUtils.rejectIfEmptyOrWhitespace(
            errors,
            "titleEnglish",
            "titleEnglish.empty",
        )
    }
}
