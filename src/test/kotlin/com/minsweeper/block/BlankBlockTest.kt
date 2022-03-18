package com.minsweeper.block

import com.minsweeper.blankBlock
import com.minsweeper.block.BlockStatus.CLOSE
import com.minsweeper.block.BlockStatus.OPEN
import com.minsweeper.blocks
import com.minsweeper.component.DefaultNumberAllocator
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

    @Test
    fun `빈 블록을 오픈하면 chord 한다`() {
        val blocks = blocks(Coordinate(5 to 5))

        blocks.mine(Coordinate(1 to 1))
        blocks.mine(Coordinate(2 to 2))
        blocks.mine(Coordinate(4 to 3))

        val allocatedBlocks: Blocks = DefaultNumberAllocator().allocate(blocks)

        val blankBlock = blocks.getOne(Coordinate(0 to 3)) as BlankBlock

        blankBlock.chord(blocks)

        assertThat(allocatedBlocks.getOne(Coordinate(0 to 2)).opened()).isTrue
        assertThat(allocatedBlocks.getOne(Coordinate(0 to 3)).opened()).isTrue
        assertThat(allocatedBlocks.getOne(Coordinate(0 to 4)).opened()).isTrue
        assertThat(allocatedBlocks.getOne(Coordinate(1 to 2)).opened()).isTrue
        assertThat(allocatedBlocks.getOne(Coordinate(1 to 3)).opened()).isTrue
        assertThat(allocatedBlocks.getOne(Coordinate(1 to 4)).opened()).isTrue
        assertThat(allocatedBlocks.getOne(Coordinate(2 to 3)).opened()).isTrue
        assertThat(allocatedBlocks.getOne(Coordinate(2 to 4)).opened()).isTrue
        assertThat(allocatedBlocks.getOne(Coordinate(3 to 3)).opened()).isTrue
        assertThat(allocatedBlocks.getOne(Coordinate(3 to 4)).opened()).isTrue

        assertThat(blocks.toList().sumOf { it.count { block -> !block.opened() } }).isEqualTo(15)
    }
}
