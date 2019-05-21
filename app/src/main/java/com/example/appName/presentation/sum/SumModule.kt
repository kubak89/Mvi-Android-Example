package com.example.appName.presentation.sum

import com.example.appName.presentation.base.BaseActivity
import dagger.Module
import dagger.Provides

@Module
class SumModule {

    @Provides
    fun provideSumView(activity: SumActivity): SumView = activity

    @Provides
    fun provideSumPresenter(
            sumView: SumView,
            initialState: SumViewState
    ): SumPresenter = SumPresenter(sumView, initialState)

    @Provides
    fun provideInitialSumViewState(activity: SumActivity): SumViewState = activity.savedInstanceState?.getSerializable(
            BaseActivity.KEY_SAVED_ACTIVITY_VIEW_STATE) as? SumViewState
            ?: SumViewState()
}