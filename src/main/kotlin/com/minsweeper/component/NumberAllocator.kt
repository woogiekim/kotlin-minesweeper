package com.minsweeper.component

import com.minsweeper.block.Blocks
import com.minsweeper.block.Coordinate
import com.minsweeper.block.MineBlock

interface NumberAllocator {
    fun allocate(blocks: Blocks): Blocks
}

class DefaultNumberAllocator : NumberAllocator {
    override fun allocate(blocks: Blocks): Blocks {
        val mineBlocks: List<MineBlock> = blocks.toMines()

        mineBlocks.forEach { regionalAllocate(it, blocks) }

        return blocks
    }

    private fun regionalAllocate(mineBlock: MineBlock, blocks: Blocks): Blocks {
        val around: List<Coordinate> = mineBlock.coordinate.getAround(blocks.coordinate)

        around.forEach(blocks::revisionNumber)

        return blocks
    }
}
