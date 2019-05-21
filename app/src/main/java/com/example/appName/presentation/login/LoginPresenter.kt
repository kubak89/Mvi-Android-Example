package com.example.appName.presentation.login

import com.example.appName.common.inputValidation.PasswordValidator
import com.example.appName.common.inputValidation.UsernameValidator
import com.example.appName.data.repository.user.UserRepository
import com.example.appName.presentation.base.BasePresenter
import io.reactivex.Observable

class LoginPresenter(
        private val view: LoginView,
        private val userRepository: UserRepository,
        initialState: LoginViewState
) : BasePresenter<LoginViewState, LoginViewState.PartialState>(initialState) {

    //region Intent methods
    override fun provideViewIntents(): List<Observable<LoginViewState.PartialState>> =
            listOf(createChangeUsernameObservable(), createChangePasswordObservable(), createRegisterObservable(), createLoginObservable())

    private fun createChangeUsernameObservable(): Observable<LoginViewState.PartialState> =
            view.changeUsernameIntent
                    .map { UsernameValidator.validate(it) }
                    .map { LoginViewState.PartialState.UsernameValidated(it) as LoginViewState.PartialState }

    private fun createChangePasswordObservable(): Observable<LoginViewState.PartialState> =
            view.changePasswordIntent
                    .map { PasswordValidator.validate(it) }
                    .map { LoginViewState.PartialState.PasswordValidated(it) as LoginViewState.PartialState }

    private fun createLoginObservable(): Observable<LoginViewState.PartialState> = view.loginIntent
            .flatMap { loginData ->
                userRepository
                        .login(loginData.username, loginData.password).toObservable()
                        .map { LoginViewState.PartialState.LoginCompleted() as LoginViewState.PartialState }
                        .doOnNext {
                            view.apply {
                                goToMainFeature()
                                finishCurrentFeature()
                            }
                        }
                        .startWith(LoginViewState.PartialState.Loading())
                        .onErrorReturn { LoginViewState.PartialState.LoginError(it) }
            }

    private fun createRegisterObservable(): Observable<LoginViewState.PartialState> = view.registerIntent
            .doOnNext { view.apply { goToRegisterFeature() } }
            .map { LoginViewState.PartialState.NoOp() }

    //endregion

    override fun reduceViewState(previousState: LoginViewState, partialState: LoginViewState.PartialState) =
            when (partialState) {
                is LoginViewState.PartialState.UsernameValidated ->
                    LoginViewState(
                            previousState,
                            usernameValidationResult = partialState.usernameValidationResult)
                is LoginViewState.PartialState.PasswordValidated ->
                    LoginViewState(
                            previousState,
                            passwordValidationResult = partialState.passwordValidationResult)
                is LoginViewState.PartialState.Loading -> LoginViewState(previousState,
                        loading = true)
                is LoginViewState.PartialState.LoginError ->
                    LoginViewState(
                            previousState,
                            error = partialState.throwable)
                is LoginViewState.PartialState.LoginCompleted ->
                    LoginViewState(
                            previousState,
                            successful = true)
                is LoginViewState.PartialState.NoOp -> previousState
            }
}
