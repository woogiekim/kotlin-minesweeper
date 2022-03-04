package com.minsweeper.block

class NumberBlock(
    val number: Int,
    val coordinate: Coordinate,
) : SimpleBlock(coordinate, "$number") {
    init {
        require(number > MINIMUM) { "숫자는 $MINIMUM 보다 커야 함" }
        require(number <= MAXIMUM) { "숫자는 $MAXIMUM 보다 작거나 같아야 함" }
    }

    fun plus(): NumberBlock = NumberBlock(number + 1, coordinate)

    companion object {
        const val MINIMUM = 0
        const val MAXIMUM = 8
    }
}
