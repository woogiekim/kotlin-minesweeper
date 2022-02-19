package com.minsweeper.block

import com.minsweeper.block.BlockStatus.*

interface Block {
    fun open()
    fun display(): String
}

abstract class SimpleBlock(
    coordinate: Coordinate,
    private val display: String,
    var status: BlockStatus = CLOSE
) : Block {
    fun canOpen(): Boolean = this.status == CLOSE

    override fun display(): String {
        return when (status) {
            CLOSE -> "◻️"
            OPEN -> this.display
            FLAG -> "🚩"
            QUESTION_MARK -> "?"
        }
    }
}
