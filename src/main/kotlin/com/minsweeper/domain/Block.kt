package com.minsweeper.domain

import com.minsweeper.domain.BlockType.*

class Block(
    var type: BlockType
) {

    fun change(type: BlockType) {
        if (!this.type.canChangeTo(type)) return

        this.type = type
    }

    companion object {
        fun createBlank(): Block = Block(BLANK)

        fun createMine(): Block = Block(MINE)

        fun createNumber(): Block = Block(NUMBER)
    }
}
