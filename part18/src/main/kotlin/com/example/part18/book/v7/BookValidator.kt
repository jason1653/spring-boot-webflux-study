package com.example.part18.book.v7

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.Errors
import org.springframework.validation.Validator
import org.springframework.web.server.ResponseStatusException

@Component("bookValidatorV7")
class BookValidator(
    private val validator: Validator,
) {
    fun <T : Any> validate(body: T) {
        val errors = BeanPropertyBindingResult(body, body::class.java.name)

        validator.validate(body, errors)

        if (!errors.allErrors.isEmpty()) {
            onValidationErrors(errors)
        }
    }

    private fun onValidationErrors(errors: Errors) {
        throw ResponseStatusException(HttpStatus.BAD_REQUEST, errors.allErrors.toString())
    }
}
