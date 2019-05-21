package com.example.appName.presentation.login

import com.example.appName.data.repository.user.UserRepository
import com.example.appName.presentation.base.BaseActivity.Companion.KEY_SAVED_ACTIVITY_VIEW_STATE
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @Provides
    fun provideLoginView(activity: LoginActivity): LoginView = activity

    @Provides
    fun provideLoginPresenter(
            loginView: LoginView,
            userRepository: UserRepository,
            initialState: LoginViewState
    ): LoginPresenter = LoginPresenter(loginView, userRepository, initialState)

    @Provides
    fun provideInitialLoginViewState(activity: LoginActivity): LoginViewState = activity.savedInstanceState?.getSerializable(
            KEY_SAVED_ACTIVITY_VIEW_STATE) as? LoginViewState
            ?: LoginViewState()
}
