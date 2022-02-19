package com.minsweeper.block

class BlankBlock(
    val coordinate: Coordinate,
    display: String = ""
) : SimpleBlock(coordinate, display) {
    override fun open() {
        check(this.status != BlockStatus.OPEN) { "이미 오픈한 블록" }

        this.status = BlockStatus.OPEN
    }
}
