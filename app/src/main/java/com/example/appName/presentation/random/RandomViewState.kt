package com.example.appName.presentation.random

import java.io.Serializable

data class RandomViewState(
        val firstRoll: Int = 0,
        val secondRoll: Int = 0
) : Serializable {

    constructor(
            previous: RandomViewState,
            firstRoll: Int = previous.firstRoll,
            secondRoll: Int = previous.secondRoll
    ) : this(firstRoll, secondRoll)

    sealed class PartialState {
        data class FirstRolled(val generatedNumber: Int) : PartialState()
        data class SecondRolled(val generatedNumber: Int) : PartialState()
    }
}
