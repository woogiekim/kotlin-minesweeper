package com.minsweeper.block

import com.minsweeper.board
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardTest {

    @Test
    fun `x by y 형태로 보드를 생성할 수 있다`() {
        val coordinate = Coordinate(3, 5)

        val board = board(coordinate)

        assertThat(board.coordinate).isEqualTo(coordinate)
    }

    @Test
    fun `정해진 개수만큼 지뢰를 심는다`() {
        val board = board()

        board.plantMine(Coordinate(0, 2))
        board.plantMine(Coordinate(1, 1))
        board.plantMine(Coordinate(2, 0))

        assertThat(board.getBlock(Coordinate(0, 2))).isExactlyInstanceOf(MineBlock::class.java)
        assertThat(board.getBlock(Coordinate(1, 1))).isExactlyInstanceOf(MineBlock::class.java)
        assertThat(board.getBlock(Coordinate(2, 0))).isExactlyInstanceOf(MineBlock::class.java)
    }

    @Test
    fun `보드에 있는 블록중에 하나를 좌표로 가져올 수 있다`() {
        val board = board()

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
