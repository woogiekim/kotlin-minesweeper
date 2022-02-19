package com.minsweeper.board

import com.minsweeper.block.BlankBlock
import com.minsweeper.block.Block
import com.minsweeper.block.MineBlock
import com.minsweeper.block.Coordinate
import java.util.stream.Collectors
import java.util.stream.IntStream

class Board(
    private var blocks: MutableList<MutableList<Block>>
) {

    fun getBlock(coordinate: Coordinate): Block = with(coordinate) { blocks[x][y] }

    fun plantMine(coordinate: Coordinate) {
        coordinate.apply {
            blocks[x][y] = MineBlock(this)
        }
    }

    fun getXCoordinate(): Int = this.blocks.size

    fun getYCoordinate(): Int = this.blocks.first().size

    companion object {
        private const val DEFAULT_SIZE = 3

        fun create(x: Int = DEFAULT_SIZE, y: Int = DEFAULT_SIZE): Board {
            val blocks = mutableListOf<MutableList<Block>>()

            repeat(x) {
                blocks.add(IntStream.range(0, y).mapToObj { BlankBlock(Coordinate(x, y)) }.collect(Collectors.toList()))
            }

            return Board(blocks)
        }
    }
}
