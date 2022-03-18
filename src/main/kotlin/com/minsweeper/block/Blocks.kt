package com.minsweeper.block

import com.minsweeper.component.BlockGenerator
import com.minsweeper.exception.MineSweeperException.ErrorCode.NOT_GREATER_THAN_ZERO_COORDINATE
import com.minsweeper.exception.MineSweeperException.ErrorCode.OUT_OF_COORDINATE
import com.minsweeper.exception.validate

class Blocks private constructor(
    private var blocks: MutableList<MutableList<Block>>
) {
    lateinit var coordinate: Coordinate

    val totalCount: Int
        get() = coordinate.x * coordinate.y

    fun getOne(coordinate: Coordinate): Block {
        validate(coordinate.x <= this.coordinate.x && coordinate.y <= this.coordinate.y) { OUT_OF_COORDINATE }

        return with(coordinate) { blocks[x][y] }
    }

    fun mine(coordinate: Coordinate) {
        coordinate.let {
            check(blocks[it.x][it.y] !is MineBlock) { "지뢰를 중복 설치할 수 없음" }

            blocks[it.x][it.y] = MineBlock(it)
        }
    }

    fun revisionNumber(coordinate: Coordinate) {
        coordinate.let {
            val target = blocks[it.x][it.y]

            if (target is MineBlock) return

            blocks[it.x][it.y] = if (target is NumberBlock) {
                target.plus()
            } else NumberBlock(1, it)
        }
    }

    fun chord(coordinate: Coordinate) {
        val block = getOne(coordinate)

        if (block !is BlankBlock) return

        block.chord(this)
    }

    fun cleared(): Boolean {
        return this.blocks.all { it.filterNot { block -> block is MineBlock }.all { block -> block.opened() } }
    }

    fun display(): String {
        return this.blocks.joinToString(System.lineSeparator()) {
            it.joinToString("\t") { block -> block.display() }
        }
    }

    fun forceDisplay(): String {
        return this.blocks.joinToString(System.lineSeparator()) {
            it.joinToString("\t") { block ->
                block.forceOpen()
                block.display()
            }
        }
    }

    fun toList(): List<List<Block>> = blocks

    fun toMines(): List<MineBlock> = blocks.flatMap { it.filterIsInstance<MineBlock>() }

    companion object {
        fun create(coordinate: Coordinate, blockGenerator: BlockGenerator): Blocks {
            validate(coordinate.x > 0 && coordinate.y > 0) { NOT_GREATER_THAN_ZERO_COORDINATE }

            return Blocks(blockGenerator.generate(coordinate)).apply { this.coordinate = coordinate }
        }
    }
}
