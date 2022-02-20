package com.minsweeper.block

import com.minsweeper.block.BlockStatus.CLOSE
import com.minsweeper.block.BlockStatus.OPEN
import com.minsweeper.numberBlock
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class NumberBlockTest {
    @Test
    fun `숫자블록을 생성할 수 있다`() {
        val coordinate = Coordinate(2 to 1)
        val number = numberBlock(3, coordinate)

        assertThat(number.display()).isEqualTo("◻️")
        assertThat(number.coordinate).isEqualTo(Coordinate(2, 1))
        assertThat(number.status).isEqualTo(CLOSE)
    }

    @Test
    fun `숫자블록을 오픈 하면 OPEN 상태로 바뀐다`() {
        val number = numberBlock(3)

        assertThat(number.status).isNotEqualTo(OPEN)

        number.open()

        assertThat(number.status).isEqualTo(OPEN)
        assertThat(number.display()).isEqualTo("3")
    }
}
