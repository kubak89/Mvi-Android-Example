package com.example.appName.presentation.random

import com.example.appName.presentation.base.BaseView
import io.reactivex.Observable

interface RandomView : BaseView {
    val rollFirstIntent: Observable<Any>
    val rollSecondIntent: Observable<Any>
}
