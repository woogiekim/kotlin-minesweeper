package com.minsweeper.block

import com.minsweeper.block.BlockStatus.CLOSE
import com.minsweeper.block.BlockStatus.OPEN
import com.minsweeper.numberBlock
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
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

    @Test
    fun `숫자블록의 숫자는 최소 1로 만들 수 있다`() {
        val atLeastOne = numberBlock(1)

        assertThat(atLeastOne.number).isEqualTo(1)

        assertThatIllegalArgumentException().isThrownBy { numberBlock(0) }
    }

    @Test
    fun `숫자블록의 숫자는 최대 8로 만들 수 있다`() {
        val atMostEight = numberBlock(8)

        assertThat(atMostEight.number).isEqualTo(8)

        assertThatIllegalArgumentException().isThrownBy { numberBlock(9) }
    }

    @Test
    fun `숫자블록은 플러스될 수 있다`() {
        val numberBlock = numberBlock()

        assertThat(numberBlock.number).isEqualTo(1)

        val addedNumberBlock = numberBlock.plus()

        assertThat(addedNumberBlock.number).isEqualTo(2)
    }

    @Test
    fun `숫자블록은 최대 8까지 플러스될 수 있다`() {
        val numberBlock = numberBlock(7)

        val addedNumberBlock = numberBlock.plus()

        assertThat(addedNumberBlock.number).isEqualTo(8)
        assertThatIllegalArgumentException().isThrownBy { addedNumberBlock.plus() }
    }
}
