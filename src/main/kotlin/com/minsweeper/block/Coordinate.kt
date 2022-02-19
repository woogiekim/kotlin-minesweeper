package com.minsweeper.block

data class Coordinate(
    val x: Int = 0,
    val y: Int = 0
) {
    init {
        require(x >= 0) { "x좌표 음수불가" }
        require(y >= 0) { "y좌표 음수불가" }
    }
}
