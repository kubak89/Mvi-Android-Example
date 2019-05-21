package com.example.appName.presentation.sum.calculation

import com.example.appName.presentation.base.BaseView
import io.reactivex.Observable

interface CalculationView : BaseView {
    val changeFirstNumberIntent: Observable<Long>
    val changeSecondNumberIntent: Observable<Long>
}