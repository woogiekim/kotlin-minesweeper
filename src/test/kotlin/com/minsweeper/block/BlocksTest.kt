package com.minsweeper.block

import com.minsweeper.blocks
import com.minsweeper.component.BlockGenerator
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
    fun `블록을 랜덤하게 섞을 수 있다`() {
        val blocks = blocks(Coordinate(100 to 100))

        val coordinates = listOf(
            Coordinate(0 to 0), Coordinate(0 to 1), Coordinate(0 to 2), Coordinate(0 to 3), Coordinate(0 to 4),
            Coordinate(0 to 5), Coordinate(0 to 6), Coordinate(0 to 7), Coordinate(0 to 8), Coordinate(0 to 9)
        )

        coordinates.forEach { blocks.mine(it) }
        assertThat(blocks.toList()[0].slice(IntRange(0, 9)).map { it is MineBlock }).containsOnly(true)

        blocks.shuffle()

        assertThat(blocks.toList()[0].slice(IntRange(0, 9)).map { it is MineBlock }).containsAnyOf(false)
    }
}
