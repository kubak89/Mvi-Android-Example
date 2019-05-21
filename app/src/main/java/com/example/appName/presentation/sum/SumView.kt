package com.example.appName.presentation.sum

import com.example.appName.presentation.base.BaseView
import io.reactivex.Observable


interface SumView : BaseView {
    val changeFirstNumberIntent: Observable<Long>
    val changeSecondNumberIntent: Observable<Long>
}