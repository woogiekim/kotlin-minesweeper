package com.minsweeper

import com.minsweeper.component.DefaultBlockGenerator
import com.minsweeper.component.DefaultMineGenerator
import com.minsweeper.game.GameManager

fun main() {
    val blockGenerator = DefaultBlockGenerator()
    val mineGenerator = DefaultMineGenerator()

    GameManager(blockGenerator, mineGenerator).run()
}