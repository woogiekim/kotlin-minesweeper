package com.minsweeper.domain

import java.util.stream.Collectors
import java.util.stream.IntStream

class Board(
    private var blocks: MutableList<MutableList<Block>>
) {

    fun getBlock(x: Int, y: Int): Block = this.blocks[x][y]

    fun plantMine(x: Int, y: Int) {
        this.blocks[x][y] = Block.createMine()
    }

    fun getXCoordinate(): Int = this.blocks.size

    fun getYCoordinate(): Int = this.blocks.first().size

    companion object {
        private const val DEFAULT_SIZE = 3

        fun create(x: Int = DEFAULT_SIZE, y: Int = DEFAULT_SIZE): Board {
            val blocks = mutableListOf<MutableList<Block>>()

            repeat(x) {
                blocks.add(IntStream.range(0, y).mapToObj { Block.createBlank() }.collect(Collectors.toList()))
            }

            return Board(blocks)
        }
    }
}
