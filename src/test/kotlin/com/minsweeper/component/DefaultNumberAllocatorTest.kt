package com.minsweeper.component

import com.minsweeper.block.Blocks
import com.minsweeper.block.Coordinate
import com.minsweeper.block.MineBlock
import com.minsweeper.block.NumberBlock
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DefaultNumberAllocatorTest {

    private lateinit var blocks: Blocks

    private val numberAllocator = DefaultNumberAllocator()

    @BeforeEach
    fun setUp() {
        blocks = Blocks.create(Coordinate.DEFAULT, DefaultBlockGenerator())
    }

    @Test
    fun `블록들에 있는 지뢰를 중심으로 숫자블록을 할당 할 수 있다`() {
        blocks.run {
            /*
            * 3 * 2
            * * * 2
            * 2 2 1
            */
            mine(Coordinate(0 to 1))
            mine(Coordinate(1 to 0))
            mine(Coordinate(1 to 1))
        }

        val allocatedBlocks = numberAllocator.allocate(blocks)

        allocatedBlocks.toList().forEach {
            it.filterNot { block -> block is MineBlock }.forEach { block -> block.open() }
        }

        assertAll(
            {
                assertThat(allocatedBlocks.getOne(Coordinate(0 to 0)) as NumberBlock)
                    .extracting(NumberBlock::number).isEqualTo(3)
                assertThat(allocatedBlocks.getOne(Coordinate(0 to 1))).isExactlyInstanceOf(MineBlock::class.java)
                assertThat(allocatedBlocks.getOne(Coordinate(0 to 2)) as NumberBlock)
                    .extracting(NumberBlock::number).isEqualTo(2)
            },
            {
                assertThat(allocatedBlocks.getOne(Coordinate(1 to 0))).isExactlyInstanceOf(MineBlock::class.java)
                assertThat(allocatedBlocks.getOne(Coordinate(1 to 1))).isExactlyInstanceOf(MineBlock::class.java)
                assertThat(allocatedBlocks.getOne(Coordinate(1 to 2)) as NumberBlock)
                    .extracting(NumberBlock::number).isEqualTo(2)
            },
            {
                assertThat(allocatedBlocks.getOne(Coordinate(2 to 0)) as NumberBlock)
                    .extracting(NumberBlock::number).isEqualTo(2)
                assertThat(allocatedBlocks.getOne(Coordinate(2 to 1)) as NumberBlock)
                    .extracting(NumberBlock::number).isEqualTo(2)
                assertThat(allocatedBlocks.getOne(Coordinate(2 to 2)) as NumberBlock)
                    .extracting(NumberBlock::number).isEqualTo(1)
            }
        )
    }
}
