package com.minsweeper.component

import com.minsweeper.block.Blocks
import com.minsweeper.block.Coordinate

interface MineGenerator {
    fun generate(blocks: Blocks, count: Int = (blocks.getTotalCount() / 3)): Blocks
}

class DefaultMineGenerator : MineGenerator {
    override fun generate(blocks: Blocks, count: Int): Blocks {
        return blocks.apply {
            var mineCount = count

            loop@ for (x in 0 until coordinate.x) {
                for (y in 0 until coordinate.y) {
                    mine(Coordinate(x to y)).also { mineCount-- }

                    if (mineCount <= 0) break@loop
                }
            }
        }
    }
}
