package com.example.appName.common.inputValidation

object PasswordValidator {
    fun validate(password: String): ValidationResult =
            when {
                password.isBlank() -> ValidationResult.BLANK
                !password.matches(Regex("^(?=.{7,}\$)\\S*[A-Z]\\S*\$")) -> ValidationResult.INVALID
                else -> ValidationResult.VALID
            }

    enum class ValidationResult {
        NOT_FILLED, BLANK, INVALID, VALID
    }
}