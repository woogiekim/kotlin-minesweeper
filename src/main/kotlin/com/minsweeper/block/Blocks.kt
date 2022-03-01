package com.minsweeper.block

import com.minsweeper.component.BlockGenerator

class Blocks private constructor(
    private var blocks: MutableList<MutableList<Block>>
) {
    lateinit var coordinate: Coordinate

    fun getOne(coordinate: Coordinate): Block = with(coordinate) { blocks[x][y] }

    fun getTotalCount(): Int = coordinate.x * coordinate.y

    fun mine(coordinate: Coordinate) {
        coordinate.apply {
            check(blocks[x][y] !is MineBlock) { "지뢰를 중복 설치할 수 없음" }

            blocks[x][y] = MineBlock(this)
        }
    }

    fun revisionNumber(coordinate: Coordinate) {
        coordinate.apply {
            val target = blocks[x][y]

            if (target is MineBlock) return

            blocks[x][y] = if (target is NumberBlock) {
                target.plus()
            } else NumberBlock(1, this)
        }
    }

    fun shuffle() {
        this.blocks.forEach { it.shuffle() }
        this.blocks.shuffle()
    }

    fun toList(): MutableList<MutableList<Block>> = blocks

    fun toMines(): List<MineBlock> = blocks.flatMap { it.filterIsInstance<MineBlock>() }

    companion object {
        fun create(coordinate: Coordinate, blockGenerator: BlockGenerator): Blocks {
            return Blocks(blockGenerator.generate(coordinate)).apply { this.coordinate = coordinate }
        }
    }
}
