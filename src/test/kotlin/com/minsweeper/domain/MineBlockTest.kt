package com.minsweeper.domain

import com.minsweeper.block.BlockStatus.CLOSE
import com.minsweeper.block.Coordinate
import com.minsweeper.block.MineBlock
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineBlockTest {

    @Test
    fun `지뢰블록을 생성할 수 있다`() {
        val mine = MineBlock(Coordinate(1, 1))

        assertThat(mine.coordinate).isEqualTo(Coordinate(1, 1))
        assertThat(mine.status).isEqualTo(CLOSE)
    }
}
