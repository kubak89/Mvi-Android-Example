package com.example.appName.presentation.random

import com.example.appName.presentation.base.BasePresenter
import io.reactivex.Observable
import java.util.*

class RandomPresenter(
        private val view: RandomView,
        private val random: Random,
        initialState: RandomViewState
) : BasePresenter<RandomViewState, RandomViewState.PartialState>(initialState) {

    override fun reduceViewState(previousState: RandomViewState, partialState: RandomViewState.PartialState): RandomViewState {
        return when (partialState) {
            is RandomViewState.PartialState.FirstRolled ->
                RandomViewState(previousState, firstRoll = partialState.generatedNumber)
            is RandomViewState.PartialState.SecondRolled ->
                RandomViewState(previousState, secondRoll = partialState.generatedNumber)
        }
    }

    override fun provideViewIntents(): List<Observable<RandomViewState.PartialState>> =
            listOf(createRollFirstObservable(), createRollSecondObservable())

    private fun createRollFirstObservable(): Observable<RandomViewState.PartialState> {
        return view.rollFirstIntent
                .map { generateRandomNumber() }
                .map { RandomViewState.PartialState.FirstRolled(it) }
    }

    private fun createRollSecondObservable(): Observable<RandomViewState.PartialState> {
        return view.rollSecondIntent
                .map { generateRandomNumber() }
                .map { RandomViewState.PartialState.SecondRolled(it) }
    }

    private fun generateRandomNumber() = (random.nextInt().and(Integer.MAX_VALUE)) % 6 + 1
}
