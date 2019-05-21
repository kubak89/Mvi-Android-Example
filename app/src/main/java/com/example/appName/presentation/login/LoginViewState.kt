package com.example.appName.presentation.login

import com.example.appName.common.inputValidation.PasswordValidator
import com.example.appName.common.inputValidation.UsernameValidator
import java.io.Serializable

data class LoginViewState(
        val usernameValidationResult: UsernameValidator.ValidationResult = UsernameValidator.ValidationResult.NOT_FILLED,
        val passwordValidationResult: PasswordValidator.ValidationResult = PasswordValidator.ValidationResult.NOT_FILLED,
        val loading: Boolean = false,
        val error: Throwable? = null,
        val loginSuccess: Boolean = false
) : Serializable {

    constructor(
            previous: LoginViewState,
            usernameValidationResult: UsernameValidator.ValidationResult = previous.usernameValidationResult,
            passwordValidationResult: PasswordValidator.ValidationResult = previous.passwordValidationResult,
            loading: Boolean = false,
            error: Throwable? = null,
            successful: Boolean = false
    ) : this(
            usernameValidationResult,
            passwordValidationResult,
            loading,
            error,
            successful)

    sealed class PartialState {
        data class UsernameValidated(val usernameValidationResult: UsernameValidator.ValidationResult) : PartialState()
        data class PasswordValidated(val passwordValidationResult: PasswordValidator.ValidationResult) : PartialState()
        data class LoginError(val throwable: Throwable) : PartialState()
        class LoginCompleted : PartialState()
        class Loading : PartialState()
        class NoOp : PartialState()
    }
}
