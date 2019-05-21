package com.example.appName.presentation.random

import com.example.appName.presentation.base.BaseActivity.Companion.KEY_SAVED_ACTIVITY_VIEW_STATE
import dagger.Module
import dagger.Provides
import java.util.*

@Module
class RandomModule {

    @Provides
    fun provideRandomView(activity: RandomActivity): RandomView = activity

    @Provides
    fun provideRandomPresenter(
            randomView: RandomView,
            initialState: RandomViewState
    ): RandomPresenter = RandomPresenter(randomView, Random(), initialState)

    @Provides
    fun provideInitialRandomViewState(activity: RandomActivity): RandomViewState = activity.savedInstanceState?.getSerializable(
            KEY_SAVED_ACTIVITY_VIEW_STATE) as? RandomViewState
            ?: RandomViewState()
}
