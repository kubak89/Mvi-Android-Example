package com.example.appName.data.repository.user

import android.app.Application
import com.example.appName.data.dataSource.UserDataSource
import com.example.appName.data.dataSource.mock.UserMockDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class UserRepositoryModule {

    @Provides
    @Named(USER_MOCK_DATA_SOURCE)
    fun provideUserMockDataSource(application: Application): UserDataSource = UserMockDataSource(application)

    @Provides
    fun provideUserRepository(@Named(USER_MOCK_DATA_SOURCE) dataSource: UserDataSource): UserRepository = UserRepository(dataSource)

    companion object {
        const val USER_MOCK_DATA_SOURCE = "userMockDataSource"
    }
}