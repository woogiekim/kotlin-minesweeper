package com.minsweeper.block

class MineBlock(
    val coordinate: Coordinate,
    display: String = "*"
) : SimpleBlock(coordinate, display) {
    override fun open() = throw IllegalStateException("지뢰를 밟음")
}
