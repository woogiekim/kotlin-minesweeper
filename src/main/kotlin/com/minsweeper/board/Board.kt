package com.minsweeper.board

import com.minsweeper.block.Blocks
import com.minsweeper.block.Coordinate

class Board(
    private val blocks: Blocks
) {

    fun getBlock(coordinate: Coordinate) = blocks.getOne(coordinate)

    fun getBlockCoordinate(): Coordinate = blocks.coordinate

    fun layMine(coordinate: Coordinate) = blocks.mine(coordinate)

    companion object {
        fun create(blocks: Blocks): Board = Board(blocks)
    }
}
