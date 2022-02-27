package com.minsweeper.component

import com.minsweeper.block.Blocks
import com.minsweeper.block.Coordinate

interface MineGenerator {
    fun generate(blocks: Blocks, count: Int = (blocks.coordinate.y / 3)): Blocks
}

class DefaultMineGenerator : MineGenerator {
    override fun generate(blocks: Blocks, count: Int): Blocks {
        return blocks.apply {
            var mineCount = count

            while (mineCount > 0) {
                (0 until coordinate.x).forEach { x ->
                    (0 until coordinate.y).forEach { y ->
                        mine(Coordinate(x to y)).also { mineCount-- }
                    }
                }
            }
        }
    }
}
