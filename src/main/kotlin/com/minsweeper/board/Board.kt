package com.minsweeper.board

import com.minsweeper.block.Block
import com.minsweeper.block.Coordinate
import com.minsweeper.block.MineBlock
import com.minsweeper.component.BlockGenerator

class Board(
    val coordinate: Coordinate,
    private var blocks: MutableList<MutableList<Block>>
) {

    fun getBlock(coordinate: Coordinate): Block = with(coordinate) { blocks[x][y] }

    fun plantMine(coordinate: Coordinate) {
        coordinate.apply {
            blocks[x][y] = MineBlock(this)
        }
    }

    companion object {
        fun create(coordinate: Coordinate, blockGenerator: BlockGenerator): Board {
            return Board(coordinate, blockGenerator.generate(coordinate))
        }
    }
}
