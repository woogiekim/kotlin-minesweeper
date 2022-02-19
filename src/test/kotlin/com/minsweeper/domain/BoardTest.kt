package com.minsweeper.domain

import com.minsweeper.block.BlankBlock
import com.minsweeper.block.Coordinate
import com.minsweeper.block.MineBlock
import com.minsweeper.board.Board
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardTest {

    @Test
    fun `x by y 형태로 보드를 생성할 수 있다`() {
        val x = 3
        val y = 5

        val board = Board.create(x, y)

        assertThat(board.getXCoordinate()).isEqualTo(x)
        assertThat(board.getYCoordinate()).isEqualTo(y)
    }

    @Test
    fun `정해진 개수만큼 지뢰를 심는다`() {
        val board = Board.create()

        board.plantMine(Coordinate(0, 2))
        board.plantMine(Coordinate(1, 1))
        board.plantMine(Coordinate(2, 0))

        assertThat(board.getBlock(Coordinate(0, 2))).isExactlyInstanceOf(MineBlock::class.java)
        assertThat(board.getBlock(Coordinate(1, 1))).isExactlyInstanceOf(MineBlock::class.java)
        assertThat(board.getBlock(Coordinate(2, 0))).isExactlyInstanceOf(MineBlock::class.java)
    }

    @Test
    fun `보드에 있는 블록중에 하나를 좌표로 가져올 수 있다`() {
        val board = Board.create()

        board.plantMine(Coordinate(0, 2))

        assertThat(board.getBlock(Coordinate(0, 0))).isExactlyInstanceOf(BlankBlock::class.java)
        assertThat(board.getBlock(Coordinate(0, 1))).isExactlyInstanceOf(BlankBlock::class.java)
        assertThat(board.getBlock(Coordinate(0, 2))).isExactlyInstanceOf(MineBlock::class.java)

        assertThat(board.getBlock(Coordinate(1, 0))).isExactlyInstanceOf(BlankBlock::class.java)
        assertThat(board.getBlock(Coordinate(1, 1))).isExactlyInstanceOf(BlankBlock::class.java)
        assertThat(board.getBlock(Coordinate(1, 2))).isExactlyInstanceOf(BlankBlock::class.java)

        assertThat(board.getBlock(Coordinate(2, 0))).isExactlyInstanceOf(BlankBlock::class.java)
        assertThat(board.getBlock(Coordinate(2, 1))).isExactlyInstanceOf(BlankBlock::class.java)
        assertThat(board.getBlock(Coordinate(2, 2))).isExactlyInstanceOf(BlankBlock::class.java)
    }
}
