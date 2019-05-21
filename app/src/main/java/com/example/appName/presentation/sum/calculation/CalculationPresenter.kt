package com.example.appName.presentation.sum.calculation

import com.example.appName.presentation.base.BasePresenter
import io.reactivex.Observable

class CalculationPresenter(
        private val view: CalculationView,
        initialState: CalculationViewState
) : BasePresenter<CalculationViewState, CalculationViewState.PartialState>(initialState) {
    private var firstNumber: Long = 0L
    private var secondNumber: Long = 0L

    override fun reduceViewState(previousState: CalculationViewState, partialState: CalculationViewState.PartialState): CalculationViewState {
        return when (partialState) {
            is CalculationViewState.PartialState.SumCalculated -> {
                CalculationViewState(
                        previousState,
                        firstNumber = firstNumber,
                        secondNumber = secondNumber,
                        sum = partialState.sum
                )
            }
        }
    }

    override fun provideViewIntents(): List<Observable<CalculationViewState.PartialState>> =
            listOf(createChangeFirstNumberObservable(), createChangeSecondNumberObservable())

    private fun createChangeFirstNumberObservable(): Observable<CalculationViewState.PartialState> {
        return view.changeFirstNumberIntent
                .map {
                    firstNumber = it
                    makeSum()
                }
                .map { CalculationViewState.PartialState.SumCalculated(it) }
    }

    private fun createChangeSecondNumberObservable(): Observable<CalculationViewState.PartialState> {
        return view.changeSecondNumberIntent
                .map {
                    secondNumber = it
                    makeSum()
                }
                .map { CalculationViewState.PartialState.SumCalculated(it) }
    }

    private fun makeSum() = firstNumber + secondNumber

}