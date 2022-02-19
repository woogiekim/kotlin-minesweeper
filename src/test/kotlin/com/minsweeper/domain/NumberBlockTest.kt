package com.minsweeper.domain

import com.minsweeper.block.BlockStatus
import com.minsweeper.block.Coordinate
import com.minsweeper.block.NumberBlock
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class NumberBlockTest {
    @Test
    fun `숫자블록을 생성할 수 있다`() {
        val number = NumberBlock(3, Coordinate(2, 1))

        assertThat(number.coordinate).isEqualTo(Coordinate(2, 1))
        assertThat(number.status).isEqualTo(BlockStatus.CLOSE)
    }
}
