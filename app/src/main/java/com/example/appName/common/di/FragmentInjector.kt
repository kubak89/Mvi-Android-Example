package com.example.appName.common.di

import com.example.appName.presentation.sum.calculation.CalculationFragment
import com.example.appName.presentation.sum.calculation.CalculationModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentInjector {

    @ContributesAndroidInjector(modules = [CalculationModule::class])
    abstract fun bindCalculationFragment(): CalculationFragment
}