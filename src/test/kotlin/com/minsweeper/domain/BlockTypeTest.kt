package com.minsweeper.domain

import com.minsweeper.domain.BlockType.BLANK
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class BlockTypeTest {

    @ParameterizedTest
    @EnumSource(value = BlockType::class, names = ["BLANK"], mode = EnumSource.Mode.EXCLUDE)
    fun `빈 블록타입은 숫자블록타입, 지뢰블록타입으로 변경할 수 있다`(type: BlockType) {
        val blank = BLANK

        assertThat(blank.canChangeTo(BLANK)).isFalse

        assertThat(blank.canChangeTo(type)).isTrue
    }

    @ParameterizedTest
    @EnumSource(value = BlockType::class, names = ["BLANK"], mode = EnumSource.Mode.EXCLUDE)
    fun `숫자타입이나 지뢰타입은 타입변경을 할 수 없다`(type: BlockType) {
        val result: List<Boolean> = BlockType.values().map { type.canChangeTo(it) }

        assertThat(result).containsOnly(false)
    }
}
