package com.example.appName.presentation.login

import com.example.appName.data.model.request.LoginRequest
import com.example.appName.presentation.base.BaseView
import io.reactivex.Observable

interface LoginView : BaseView {
    val changeUsernameIntent: Observable<String>
    val changePasswordIntent: Observable<String>
    val loginIntent: Observable<LoginRequest>
    val registerIntent: Observable<Any>
}
