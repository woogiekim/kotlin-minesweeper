package com.minsweeper.block

data class Coordinate(
    val pair: Pair<Int, Int> = 0 to 0
) {
    val x: Int = pair.first
    val y: Int = pair.second

    fun getAround(maximum: Coordinate): List<Coordinate> {
        return listOf(
            Coordinate((x - 1) to (y - 1)), Coordinate((x - 1) to (y)), Coordinate((x - 1) to (y + 1)),
            Coordinate(x to (y - 1)), Coordinate(x to (y + 1)),
            Coordinate((x + 1) to (y - 1)), Coordinate((x + 1) to (y)), Coordinate((x + 1) to (y + 1)),
        ).filter { it.canUse(maximum) }
    }

    private fun canUse(maximum: Coordinate): Boolean {
        return isIn(maximum.x) { it.x } && isIn(maximum.y) { it.y }
    }

    private fun isIn(maximum: Int, function: Function1<Coordinate, Int>): Boolean {
        return function.invoke(this) in 0 until maximum
    }

    companion object {
        val DEFAULT = Coordinate(3 to 3)
    }
}
