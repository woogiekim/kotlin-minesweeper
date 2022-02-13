package com.minsweeper.domain

import com.minsweeper.domain.BlockType.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class BlockTest {

    @Test
    fun `빈 블록을 생성할 수 있다`() {
        val blank = Block.createBlank()

        assertThat(blank.type).isEqualTo(BLANK)
    }

    @Test
    fun `지뢰 블록을 생성할 수 있다`() {
        val mine = Block.createMine()

        assertThat(mine.type).isEqualTo(MINE)
    }

    @Test
    fun `숫자 블록을 생성할 수 있다`() {
        val number = Block.createNumber()

        assertThat(number.type).isEqualTo(NUMBER)
    }

    @ParameterizedTest
    @EnumSource(value = BlockType::class, names = ["BLANK"], mode = EnumSource.Mode.EXCLUDE)
    fun `블록의 타입을 변경할 수 있다`(type: BlockType) {
        val blank = Block.createBlank()

        assertThat(blank.type).isEqualTo(BLANK)

        blank.change(type)

        assertThat(blank.type).isEqualTo(type)
    }

    @Test
    fun `지뢰타입 블록은 타입 변경할 수 없다`() {
        val mine = Block.createMine()

        assertThat(mine.type).isEqualTo(MINE)

        mine.change(NUMBER)

        assertThat(mine.type).isEqualTo(MINE)
    }

    @Test
    fun `숫자타입 블록은 타입 변경할 수 없다`() {
        val number = Block.createNumber()

        assertThat(number.type).isEqualTo(NUMBER)

        number.change(MINE)

        assertThat(number.type).isEqualTo(NUMBER)
    }
}
