package com.minsweeper.block

import com.minsweeper.blocks
import com.minsweeper.component.BlockGenerator
import com.minsweeper.component.DefaultNumberAllocator
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalStateException
import org.junit.jupiter.api.Test

class BlocksTest {

    @Test
    fun `블록들을 생성할 수 있다`() {
        val coordinate = Coordinate(3 to 3)
        val prepareBlocks: MutableList<MutableList<Block>> = mutableListOf(
            mutableListOf(BlankBlock(Coordinate(0 to 0))),
            mutableListOf(BlankBlock(Coordinate(0 to 1))),
            mutableListOf(BlankBlock(Coordinate(0 to 2))),
            mutableListOf(BlankBlock(Coordinate(1 to 0))),
            mutableListOf(BlankBlock(Coordinate(1 to 1))),
            mutableListOf(BlankBlock(Coordinate(1 to 2))),
            mutableListOf(BlankBlock(Coordinate(2 to 0))),
            mutableListOf(BlankBlock(Coordinate(2 to 1))),
            mutableListOf(BlankBlock(Coordinate(2 to 2)))
        )

        val blocks = blocks(coordinate, object : BlockGenerator {
            override fun generate(coordinate: Coordinate): MutableList<MutableList<Block>> = prepareBlocks
        })

        assertThat(blocks.coordinate).isEqualTo(coordinate)
        assertThat(blocks.toList()).isEqualTo(prepareBlocks)
    }

    @Test
    fun `블록들 중에 블록 하나를 가져온다`() {
        val blocks = blocks()

        assertThat(blocks.getOne(Coordinate(0 to 0))).isNotNull
    }

    @Test
    fun `블록들에 지뢰하나를 설치한다`() {
        val blocks = blocks()

        assertThat(blocks.getOne(Coordinate(0 to 0))).isNotExactlyInstanceOf(MineBlock::class.java)

        blocks.mine(Coordinate(0 to 0))

        assertThat(blocks.getOne(Coordinate(0 to 0))).isExactlyInstanceOf(MineBlock::class.java)
    }

    @Test
    fun `중복으로 지뢰를 설치할 수 없다`() {
        val blocks = blocks()

        assertThat(blocks.getOne(Coordinate(0 to 0))).isNotExactlyInstanceOf(MineBlock::class.java)

        blocks.mine(Coordinate(0 to 0))

        assertThat(blocks.getOne(Coordinate(0 to 0))).isExactlyInstanceOf(MineBlock::class.java)

        assertThatIllegalStateException().isThrownBy { blocks.mine(Coordinate(0 to 0)) }
    }

    @Test
    fun `chord 실행 성공`() {
        val blocks = blocks(Coordinate(5 to 5))

        blocks.mine(Coordinate(1 to 1))
        blocks.mine(Coordinate(2 to 2))
        blocks.mine(Coordinate(4 to 3))

        val allocatedBlocks: Blocks = DefaultNumberAllocator().allocate(blocks)

        allocatedBlocks.chord(Coordinate(0 to 3))

        println(allocatedBlocks.display())

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
