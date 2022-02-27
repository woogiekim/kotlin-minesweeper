package com.minsweeper.block

class NumberBlock(
    val number: Int,
    val coordinate: Coordinate,
) : SimpleBlock(coordinate, "$number") {
    override fun open() {
        check(this.status != BlockStatus.OPEN) { "이미 오픈한 블록" }

        this.status = BlockStatus.OPEN
    }

    companion object {
        const val MINIMUM = 0
        const val MAXIMUM = 8
    }
}
