package com.minsweeper.block

import com.minsweeper.blankBlock
import com.minsweeper.block.BlockStatus.CLOSE
import com.minsweeper.block.BlockStatus.OPEN
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlankBlockTest {

    @Test
    fun `빈 블록을 생성할 수 있다`() {
        val coordinate = Coordinate(0 to 1)
        val blank = blankBlock(coordinate)

        assertThat(blank.display()).isEqualTo("◻️")
        assertThat(blank.coordinate).isEqualTo(coordinate)
        assertThat(blank.status).isEqualTo(CLOSE)
    }

    @Test
    fun `빈 블록을 오픈 하면 OPEN 상태로 바뀐다`() {
        val blank = blankBlock()

        assertThat(blank.status).isNotEqualTo(OPEN)

        blank.open()

        assertThat(blank.status).isEqualTo(OPEN)
        assertThat(blank.display()).isEqualTo(" ")
    }
}
