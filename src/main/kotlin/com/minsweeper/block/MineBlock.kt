package com.minsweeper.block

class MineBlock(
    coordinate: Coordinate,
    display: String = "💣"
) : SimpleBlock(coordinate, display) {
    override fun open() = throw IllegalStateException("지뢰를 밟음")
}
