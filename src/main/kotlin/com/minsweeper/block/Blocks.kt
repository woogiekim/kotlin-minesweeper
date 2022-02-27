package com.minsweeper.block

import com.minsweeper.component.BlockGenerator

class Blocks private constructor(
    private var blocks: MutableList<MutableList<Block>>
) {
    lateinit var coordinate: Coordinate

    fun getOne(coordinate: Coordinate): Block = with(coordinate) { blocks[x][y] }

    fun totalCount(): Int = coordinate.x * coordinate.y

    fun mine(coordinate: Coordinate) {
        coordinate.apply {
            check(blocks[x][y] !is MineBlock) { "지뢰를 중복 설치할 수 없음" }

            blocks[x][y] = MineBlock(this)
        }
    }

    fun toList(): MutableList<MutableList<Block>> = blocks

    companion object {
        fun create(coordinate: Coordinate, blockGenerator: BlockGenerator): Blocks {
            return Blocks(blockGenerator.generate(coordinate)).apply { this.coordinate = coordinate }
        }
    }
}
