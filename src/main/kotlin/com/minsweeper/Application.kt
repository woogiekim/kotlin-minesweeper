package com.minsweeper

import com.minsweeper.component.DefaultBlockGenerator
import com.minsweeper.component.DefaultMineGenerator
import com.minsweeper.component.DefaultNumberAllocator
import com.minsweeper.game.GameManager

fun main() {
    val blockGenerator = DefaultBlockGenerator()
    val mineGenerator = DefaultMineGenerator()
    val numberAllocator = DefaultNumberAllocator()

    GameManager(blockGenerator, mineGenerator, numberAllocator).start()
}
