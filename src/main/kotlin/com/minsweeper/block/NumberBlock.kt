package com.minsweeper.block

class NumberBlock(
    number: Int,
    val coordinate: Coordinate,
) : SimpleBlock(coordinate, "$number") {
    override fun open() {
        check(this.status != BlockStatus.OPEN) { "이미 오픈한 블록" }

        this.status = BlockStatus.OPEN
    }
}
