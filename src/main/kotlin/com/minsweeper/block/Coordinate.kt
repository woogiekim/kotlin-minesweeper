package com.minsweeper.block

data class Coordinate(
    val x: Int = 0,
    val y: Int = 0
) {
    constructor(pair: Pair<Int, Int>) : this(pair.first, pair.second)

    fun getAround(maximum: Coordinate): List<Coordinate> {
        return listOf(
            Coordinate((x - 1), (y - 1)), Coordinate((x - 1), (y)), Coordinate((x - 1), (y + 1)),
            Coordinate(x, (y - 1)), Coordinate(x, (y + 1)),
            Coordinate((x + 1), (y - 1)), Coordinate((x + 1), (y)), Coordinate((x + 1), (y + 1)),
        ).filter { it.canUse(maximum) }
    }

    private fun canUse(maximum: Coordinate): Boolean {
        return isIn(maximum.x) { it.x } && isIn(maximum.y) { it.y }
    }

    private fun isIn(maximum: Int, function: Function1<Coordinate, Int>): Boolean {
        return function.invoke(this) in 0 until maximum
    }

    companion object {
        val DEFAULT = Coordinate(3, 3)
    }
}
