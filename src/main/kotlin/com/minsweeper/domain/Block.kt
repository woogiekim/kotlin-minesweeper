package com.minsweeper.domain

import com.minsweeper.domain.BlockStatus.*

class Block(
    var status: BlockStatus
) {
    companion object {
        fun createUnknown(): Block = Block(UNKNOWN)

        fun createBlank(): Block = Block(BLANK)

        fun createMine(): Block = Block(MINE)

        fun createNumber(): Block = Block(NUMBER)
    }
}
