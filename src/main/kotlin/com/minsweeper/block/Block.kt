package com.minsweeper.block

import com.minsweeper.block.BlockStatus.CLOSE

interface Block {
    fun display(): String
    fun open()
}

abstract class SimpleBlock(
    coordinate: Coordinate,
    var status: BlockStatus = CLOSE
) : Block {
    fun canOpen(): Boolean = this.status == CLOSE
}
