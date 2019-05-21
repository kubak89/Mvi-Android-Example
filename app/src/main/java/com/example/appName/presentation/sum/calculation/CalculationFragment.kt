package com.example.appName.presentation.sum.calculation

import com.example.appName.R
import com.example.appName.presentation.base.BaseFragment
import com.jakewharton.rxbinding2.widget.textChanges
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_calculation.*

class CalculationFragment : BaseFragment<CalculationViewState, CalculationPresenter>(
        R.layout.fragment_calculation
), CalculationView {

    override val changeFirstNumberIntent: Observable<Long> by lazy {
        firstNumber.textChanges().map { it.toString().toLongOrNull() ?: 0 }.skip(1)
    }

    override val changeSecondNumberIntent: Observable<Long> by lazy {
        secondNumber.textChanges().map { it.toString().toLongOrNull() ?: 0 }.skip(1)
    }

    override fun render(viewState: CalculationViewState) {
        sum.text = viewState.sum.toString()
    }

    companion object {
        fun newInstance(): CalculationFragment = CalculationFragment()
    }
}