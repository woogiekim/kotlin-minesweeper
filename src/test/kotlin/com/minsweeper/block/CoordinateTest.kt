package com.minsweeper.block

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CoordinateTest {

    @Test
    fun `좌표 주변에 둘러 쌓여있는 8개의 좌표를 가져온다`() {
        val base = Coordinate(1 to 1)

        val around = base.getAround(Coordinate.DEFAULT)

        assertThat(around).containsExactly(
            Coordinate(0 to 0), Coordinate(0 to 1), Coordinate(0 to 2),
            Coordinate(1 to 0), Coordinate(1 to 2),
            Coordinate(2 to 0), Coordinate(2 to 1), Coordinate(2 to 2)
        )
    }

    @Test
    fun `음수가 포함되는 좌표는 제외하고 가져온다`() {
        val base = Coordinate(0 to 0)

        val around = base.getAround(Coordinate.DEFAULT)

        assertThat(around).containsExactly(
            Coordinate(0 to 1),
            Coordinate(1 to 0), Coordinate(1 to 1)
        )
    }
}
