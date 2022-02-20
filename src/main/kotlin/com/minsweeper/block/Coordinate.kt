package com.minsweeper.block

data class Coordinate(
    val x: Int = 0,
    val y: Int = 0
) {
    constructor(pair: Pair<Int, Int>) : this(pair.first, pair.second)

    init {
        require(x >= 0) { "x좌표 음수불가" }
        require(y >= 0) { "y좌표 음수불가" }
    }

    companion object {
        val DEFAULT = Coordinate(3, 3)
    }
}
