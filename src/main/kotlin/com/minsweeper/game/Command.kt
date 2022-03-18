package com.minsweeper.game

import com.minsweeper.block.BlankBlock
import com.minsweeper.block.Block
import com.minsweeper.block.Blocks

enum class Command {
    OPEN,
    CLOSE,
    FLAG,
    QUESTION_MARK;

    fun action(block: Block, blocks: Blocks) {
        when (this) {
            OPEN -> {
                if (block is BlankBlock) {
                    block.chord(blocks)
                } else {
                    block.open()
                }
            }
            CLOSE -> block.close()
            FLAG -> block.flag()
            QUESTION_MARK -> block.skip()
        }
    }

    companion object {
        fun of(code: Int): Command {
            return values().first { (it.ordinal + 1) == code }
        }

        fun display(): String {
            return values().joinToString(System.lineSeparator()) { "${it.ordinal + 1}. ${it.name}" }
        }
    }
}
