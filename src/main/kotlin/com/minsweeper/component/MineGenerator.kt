package com.minsweeper.component

import com.minsweeper.block.Blocks
import com.minsweeper.block.Coordinate
import com.minsweeper.block.MineBlock
import kotlin.random.Random
import kotlin.random.nextInt

interface MineGenerator {
    fun generate(blocks: Blocks, count: Int = (blocks.getTotalCount() / 3)): Blocks
}

class DefaultMineGenerator : MineGenerator {
    override fun generate(blocks: Blocks, count: Int): Blocks {
        return blocks.apply {
            var mineCount = count

            while (mineCount > 0) {
                val randomX = Random.nextInt(0, coordinate.x)
                val randomY = Random.nextInt(0, coordinate.y)
                val randomCoordinate = Coordinate(randomX to randomY)

                val candidateBlock = getOne(randomCoordinate)

                if (candidateBlock !is MineBlock) {
                    mine(randomCoordinate).also { mineCount-- }
                }
            }
        }
    }
}
