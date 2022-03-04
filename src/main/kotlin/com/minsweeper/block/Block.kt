package com.minsweeper.block

import com.minsweeper.block.BlockStatus.*

interface Block {
    fun open()
    fun close()
    fun flag()
    fun skip()
    fun display(): String
}

abstract class SimpleBlock(
    coordinate: Coordinate,
    private val display: String,
    var status: BlockStatus = CLOSE
) : Block {
    override fun open() {
        check(this.status != OPEN) { "이미 오픈한 블록" }

        this.status = OPEN
    }

    override fun close() {
        check(this.status != CLOSE) { "이미 닫은 블록" }

        this.status = CLOSE
    }

    override fun flag() {
        check(this.status != FLAG) { "이미 플래그한 블록" }

        this.status = FLAG
    }

    override fun skip() {
        check(this.status != QUESTION_MARK) { "이미 ? 한 블록" }

        this.status = QUESTION_MARK
    }

    override fun display(): String {
        return when (status) {
            CLOSE -> "◻️"
            OPEN -> this.display
            FLAG -> "🚩"
            QUESTION_MARK -> "❓"
        }
    }
}
