package com.example.appName.presentation.sum.calculation

import com.example.appName.presentation.base.BaseActivity
import dagger.Module
import dagger.Provides

@Module
class CalculationModule {

    @Provides
    fun provideCalculationView(fragmen: CalculationFragment): CalculationView = fragmen

    @Provides
    fun provideCalculationPresenter(
            sumView: CalculationView,
            initialState: CalculationViewState
    ): CalculationPresenter = CalculationPresenter(sumView, initialState)

    @Provides
    fun provideInitialSumViewState(fragment: CalculationFragment): CalculationViewState = fragment.savedInstanceState?.getSerializable(
            BaseActivity.KEY_SAVED_ACTIVITY_VIEW_STATE) as? CalculationViewState
            ?: CalculationViewState()
}