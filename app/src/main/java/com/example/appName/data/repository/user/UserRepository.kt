package com.example.appName.data.repository.user

import com.example.appName.data.dataSource.UserDataSource
import com.example.appName.data.model.domain.User
import io.reactivex.Single

class UserRepository(val userDataSource: UserDataSource) {
    fun login(username: String, password: String): Single<User> {
        return userDataSource.login(username, password)
    }
}