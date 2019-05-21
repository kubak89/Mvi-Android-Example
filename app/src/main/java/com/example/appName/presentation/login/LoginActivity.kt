package com.example.appName.presentation.login

import com.example.appName.R
import com.example.appName.common.extension.createTextChangesObservable
import com.example.appName.common.extension.getMessage
import com.example.appName.common.inputValidation.PasswordValidator
import com.example.appName.common.inputValidation.UsernameValidator
import com.example.appName.data.model.request.LoginRequest
import com.example.appName.presentation.base.BaseActivity
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.alert

class LoginActivity : BaseActivity<LoginViewState, LoginPresenter>(
        R.layout.activity_login
), LoginView {
    //region Intents
    override val changeUsernameIntent: Observable<String> by lazy {
        loginUsername.createTextChangesObservable()
    }

    override val changePasswordIntent: Observable<String> by lazy {
        loginPassword.createTextChangesObservable()
    }

    override val loginIntent: Observable<LoginRequest> by lazy {
        RxView.clicks(loginButton).map {
            val username = loginUsername.text.toString()
            val password = loginPassword.text.toString()
            LoginRequest(username, password)
        }
    }

    override val registerIntent: Observable<Any> by lazy {
        RxView.clicks(loginRegisterButton)
    }
    //endregion

    //region Render methods
    override fun render(viewState: LoginViewState) {
        if (viewState.loginSuccess) {
            return
        }

        renderLoading(viewState)

        renderValidationResults(viewState)

        loginButton.isEnabled = allInputsAreValid(viewState)

    }

    private fun renderLoading(viewState: LoginViewState) {
        if (viewState.loading) {
            showChildIfNotShownAlready(LOADING_VIEW_INDEX)
        } else {
            showChildIfNotShownAlready(MAIN_VIEW_INDEX)
        }
    }

    private fun renderValidationResults(viewState: LoginViewState) {
        loginUsername.error = viewState.usernameValidationResult.getMessage(this)
        loginPassword.error = viewState.passwordValidationResult.getMessage(this)

        if (viewState.error != null) {
            alert { message = (viewState.error.getMessage(this@LoginActivity)) }.show()
        }
    }

    private fun showChildIfNotShownAlready(childIndex: Int) {
        loginLoadingSwitcher.apply {
            if (displayedChild != childIndex)
                displayedChild = childIndex
        }
    }

    private fun allInputsAreValid(viewState: LoginViewState) =
            (viewState.usernameValidationResult == UsernameValidator.ValidationResult.VALID &&
                    viewState.passwordValidationResult == PasswordValidator.ValidationResult.VALID)
    //endregion

    companion object {
        private const val MAIN_VIEW_INDEX = 0
        private const val LOADING_VIEW_INDEX = 1
    }
}
