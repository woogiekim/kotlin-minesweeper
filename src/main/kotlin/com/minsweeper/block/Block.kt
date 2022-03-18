package com.minsweeper.block

import com.minsweeper.block.BlockStatus.*

interface Block {
    fun open()
    fun close()
    fun flag()
    fun skip()
    fun forceOpen()
    fun opened(): Boolean
    fun display(): String
}

abstract class SimpleBlock(
    var coordinate: Coordinate,
    private val display: String,
    var status: BlockStatus = CLOSE
) : Block {
    override fun open() {
        changeStatus(OPEN)
    }

    override fun close() {
        changeStatus(CLOSE)
    }

    override fun flag() {
        changeStatus(FLAG)
    }

    override fun skip() {
        changeStatus(QUESTION_MARK)
    }

    final override fun forceOpen() {
        changeStatus(OPEN)
    }

    private fun changeStatus(status: BlockStatus) {
        this.status = status
    }

    override fun opened(): Boolean {
        return this.status == OPEN
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
