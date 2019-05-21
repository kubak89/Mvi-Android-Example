package com.example.appName.data.dataSource

import com.example.appName.data.model.domain.User
import io.reactivex.Single

interface UserDataSource {
    fun login(username: String, password: String): Single<User>
}