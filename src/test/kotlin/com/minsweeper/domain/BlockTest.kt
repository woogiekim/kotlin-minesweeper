package com.minsweeper.domain

import com.minsweeper.domain.BlockStatus.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlockTest {

    @Test
    fun `빈 블록을 생성할 수 있다`() {
        val blank = Block.createBlank()

        assertThat(blank.status).isEqualTo(BLANK)
    }

    @Test
    fun `지뢰 블록을 생성할 수 있다`() {
        val mine = Block.createMine()

        assertThat(mine.status).isEqualTo(MINE)
    }

    @Test
    fun `숫자 블록을 생성할 수 있다`() {
        val number = Block.createNumber()

        assertThat(number.status).isEqualTo(NUMBER)
    }
}
