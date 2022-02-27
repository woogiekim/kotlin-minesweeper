package com.minsweeper.component

import com.minsweeper.block.Blocks
import com.minsweeper.block.Coordinate
import com.minsweeper.block.MineBlock
import com.minsweeper.blocks
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DefaultMineGeneratorTest {
    private val mineGenerator = DefaultMineGenerator()

    @Test
    fun `지뢰를 심을 수 있다 (개수를 정하지 않으면, 기본적으로 총 블록수의 3 분의 1 만큼 지뢰를 심는다)`() {
        val blocks: Blocks = blocks(Coordinate(6 to 6))

        val minedBlocks: Blocks = mineGenerator.generate(blocks)

        `지뢰 설치 검증`(minedBlocks, (blocks.getTotalCount() / 3))
    }

    @Test
    fun `정한 개수 만큼 지뢰를 심을 수 있다`() {
        val blocks: Blocks = blocks()
        val mineCount = 3

        val minedBlocks: Blocks = mineGenerator.generate(blocks, mineCount)

        `지뢰 설치 검증`(minedBlocks, mineCount)
    }

    private fun `지뢰 설치 검증`(minedBlocks: Blocks, count: Int) {
        assertThat(minedBlocks.toList().sumOf { it.count { block -> block is MineBlock } }).isEqualTo(count)
    }
}
