package com.minsweeper.domain

import com.minsweeper.domain.BlockType.BLANK
import com.minsweeper.domain.BlockType.MINE
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

        board.plantMine(0, 2)
        board.plantMine(1, 1)
        board.plantMine(2, 0)

        assertThat(board.getBlock(0, 2).type).isEqualTo(MINE)
        assertThat(board.getBlock(1, 1).type).isEqualTo(MINE)
        assertThat(board.getBlock(2, 0).type).isEqualTo(MINE)
    }

    @Test
    fun `보드에 있는 블록중에 하나를 좌표로 가져올 수 있다`() {
        val board = Board.create()

        board.plantMine(0, 2)

        assertThat(board.getBlock(0, 0).type).isEqualTo(BLANK)
        assertThat(board.getBlock(0, 1).type).isEqualTo(BLANK)
        assertThat(board.getBlock(0, 2).type).isEqualTo(MINE)

        assertThat(board.getBlock(1, 0).type).isEqualTo(BLANK)
        assertThat(board.getBlock(1, 1).type).isEqualTo(BLANK)
        assertThat(board.getBlock(1, 2).type).isEqualTo(BLANK)

        assertThat(board.getBlock(2, 0).type).isEqualTo(BLANK)
        assertThat(board.getBlock(2, 1).type).isEqualTo(BLANK)
        assertThat(board.getBlock(2, 2).type).isEqualTo(BLANK)
    }
}
