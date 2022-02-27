package com.minsweeper.component

import com.minsweeper.block.*
import com.minsweeper.blocks
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DefaultMineGeneratorTest {
    private val mineGenerator = DefaultMineGenerator()

    private lateinit var blocks: Blocks

    @BeforeEach
    internal fun setUp() {
        blocks = blocks()
    }

    @Test
    fun `지뢰를 심을 수 있다`() {
        val minedBlocks: Blocks = mineGenerator.generate(blocks)

        `지뢰 설치 검증`(minedBlocks, 1)
    }

    @Test
    fun `정한 개수 만큼 지뢰를 심을 수 있다`() {
        val mineCount = 3
        val minedBlocks: Blocks = mineGenerator.generate(blocks, mineCount)

        `지뢰 설치 검증`(minedBlocks, mineCount)
    }

    @Test
    fun `총 블록보다 많이 지뢰를 심을 수 없다`() {
        val mineCount = blocks.totalCount() + 1

        assertThatIllegalStateException().isThrownBy { mineGenerator.generate(blocks, mineCount) }
    }

    private fun `지뢰 설치 검증`(minedBlocks: Blocks, count: Int) {
        assertThat(minedBlocks.toList().count { list -> list.any { it is MineBlock } }).isEqualTo(count)
    }
}
