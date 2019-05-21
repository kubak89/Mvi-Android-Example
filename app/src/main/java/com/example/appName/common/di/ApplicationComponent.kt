package com.example.appName.common.di

import android.app.Application
import com.example.appName.MyApplication
import com.example.appName.data.repository.user.UserRepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule


@Component(modules = [
    AndroidSupportInjectionModule::class,
    ApplicationModule::class,
    ActivityInjector::class,
    FragmentInjector::class,
    UserRepositoryModule::class
])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: MyApplication)
}