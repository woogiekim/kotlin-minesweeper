package com.minsweeper.domain

enum class BlockType(
    private vararg val types: BlockType
) {
    MINE,
    NUMBER,
    BLANK(NUMBER, MINE);

    fun canChangeTo(type: BlockType): Boolean = type in types.toList()
}
