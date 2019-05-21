package com.example.appName.common.di

import com.example.appName.presentation.login.LoginActivity
import com.example.appName.presentation.login.LoginModule
import com.example.appName.presentation.random.RandomActivity
import com.example.appName.presentation.random.RandomModule
import com.example.appName.presentation.sum.SumActivity
import com.example.appName.presentation.sum.SumModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityInjector {

    @ContributesAndroidInjector(modules = [RandomModule::class])
    abstract fun bindRandomActivity(): RandomActivity

    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [SumModule::class])
    abstract fun bindSumActivity(): SumActivity
}