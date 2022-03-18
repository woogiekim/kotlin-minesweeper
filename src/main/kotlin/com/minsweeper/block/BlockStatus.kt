package com.minsweeper.block

enum class BlockStatus(
    val code: Int
) {
    CLOSE(1),
    OPEN(2),
    FLAG(3),
    QUESTION_MARK(4);

    companion object {
        fun of(code: Int): BlockStatus {
            return values().first { it.code == code }
        }
    }
}
