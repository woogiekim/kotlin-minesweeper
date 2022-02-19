package com.minsweeper.block

class MineBlock(
    val coordinate: Coordinate
) : SimpleBlock(coordinate) {
    override fun display(): String = "*"

    override fun open() = throw IllegalStateException("지뢰를 밟음")
}
