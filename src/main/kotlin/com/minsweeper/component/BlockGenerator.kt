package com.minsweeper.component

import com.minsweeper.block.BlankBlock
import com.minsweeper.block.Block
import com.minsweeper.block.Coordinate
import java.util.stream.Collectors
import java.util.stream.IntStream

interface BlockGenerator {
    fun generate(coordinate: Coordinate): MutableList<MutableList<Block>>
}

class DefaultBlockGenerator : BlockGenerator {
    override fun generate(coordinate: Coordinate): MutableList<MutableList<Block>> {
        val blocks: MutableList<MutableList<Block>> = mutableListOf()

        repeat(coordinate.x) { x ->
            blocks.add(IntStream.range(0, coordinate.y)
                .mapToObj { y -> BlankBlock(Coordinate(x, y)) }
                .collect(Collectors.toList()))
        }

        return blocks
    }
}
