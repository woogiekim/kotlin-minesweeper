package com.minsweeper.domain

import com.minsweeper.block.BlankBlock
import com.minsweeper.block.BlockStatus.CLOSE
import com.minsweeper.block.Coordinate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlankBlockTest {

    @Test
    fun `빈 블록을 생성할 수 있다`() {
        val blank = BlankBlock(Coordinate(0, 1))

        assertThat(blank.coordinate).isEqualTo(Coordinate(0, 1))
        assertThat(blank.status).isEqualTo(CLOSE)
    }
}
