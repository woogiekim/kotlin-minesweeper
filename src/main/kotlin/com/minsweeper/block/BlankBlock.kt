package com.minsweeper.block

class BlankBlock(
    val coordinate: Coordinate
) : SimpleBlock(coordinate) {
    override fun display(): String = ""

    override fun open() {
        check(this.status != BlockStatus.OPEN) { "이미 오픈한 블록" }

        this.status = BlockStatus.OPEN
    }
}
