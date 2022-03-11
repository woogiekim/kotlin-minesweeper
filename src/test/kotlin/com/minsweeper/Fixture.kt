package com.minsweeper

import com.minsweeper.block.*
import com.minsweeper.block.Coordinate.Companion.DEFAULT
import com.minsweeper.board.Board
import com.minsweeper.component.BlockGenerator
import com.minsweeper.component.DefaultBlockGenerator
import com.minsweeper.exception.MineSweeperException
import org.assertj.core.api.Assertions.assertThatExceptionOfType

fun assertThatMineSweeperException(errorCode: MineSweeperException.ErrorCode, block: () -> Any) {
    assertThatExceptionOfType(MineSweeperException::class.java).isThrownBy { block() }
        .extracting("errorCode")
        .isEqualTo(errorCode)
}

fun blankBlock(
    coordinate: Coordinate = Coordinate(0 to 0)
): BlankBlock = BlankBlock(coordinate)

fun numberBlock(
    number: Int = 1,
    coordinate: Coordinate = Coordinate(0 to 1)
): NumberBlock = NumberBlock(number, coordinate)

fun mineBlock(
    coordinate: Coordinate = Coordinate(0 to 2)
): MineBlock = MineBlock(coordinate)

fun blocks(
    coordinate: Coordinate = DEFAULT,
    blockGenerator: BlockGenerator = DefaultBlockGenerator()
): Blocks = Blocks.create(coordinate, blockGenerator)

fun board(blocks: Blocks = blocks()): Board = Board.create(blocks)
