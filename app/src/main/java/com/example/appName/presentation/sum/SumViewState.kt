package com.example.appName.presentation.sum

import java.io.Serializable

data class SumViewState(
        val firstNumber: Long = 0,
        val secondNumber: Long = 0,
        val sum: Long = 0
) : Serializable {

    constructor(
            previous: SumViewState,
            firstNumber: Long = previous.firstNumber,
            secondNumber: Long = previous.secondNumber,
            sum: Long = firstNumber + secondNumber
    ) : this(firstNumber, secondNumber, sum)

    sealed class PartialState {
        data class SumCalculated(val sum: Long) : PartialState()
    }
}