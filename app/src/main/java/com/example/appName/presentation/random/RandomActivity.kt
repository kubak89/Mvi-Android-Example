package com.example.appName.presentation.random

import com.example.appName.R
import com.example.appName.presentation.base.BaseActivity
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_random.*

class RandomActivity : BaseActivity<RandomViewState, RandomPresenter>(
        R.layout.activity_random
), RandomView {
    //region intents
    override val rollFirstIntent: Observable<Any> by lazy {
        RxView.clicks(randomRollFirstNumber)
    }

    override val rollSecondIntent: Observable<Any> by lazy {
        RxView.clicks(randomRollSecondNumber)
    }
    //endregion

    override fun render(viewState: RandomViewState) {
        randomFirstNumber.text = viewState.firstRoll.toString()
        randomSecondNumber.text = viewState.secondRoll.toString()
    }
}
