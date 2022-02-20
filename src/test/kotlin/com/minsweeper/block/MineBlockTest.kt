package com.minsweeper.block

import com.minsweeper.block.BlockStatus.CLOSE
import com.minsweeper.block.BlockStatus.OPEN
import com.minsweeper.mineBlock
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalStateException
import org.junit.jupiter.api.Test

class MineBlockTest {

    @Test
    fun `지뢰블록을 생성할 수 있다`() {
        val coordinate = Coordinate(1 to 1)
        val mine = mineBlock(coordinate)

        assertThat(mine.display()).isEqualTo("◻️")
        assertThat(mine.coordinate).isEqualTo(coordinate)
        assertThat(mine.status).isEqualTo(CLOSE)
    }

    @Test
    fun `지뢰블록을 오픈하면 예외가 발생한다`() {
        val mine = mineBlock()

        assertThat(mine.status).isNotEqualTo(OPEN)

        assertThatIllegalStateException().isThrownBy { mine.open() }
    }
}
