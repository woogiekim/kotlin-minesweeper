package com.minsweeper.block

class BlankBlock(
    coordinate: Coordinate,
    display: String = " "
) : SimpleBlock(coordinate, display) {
    fun chord(blocks: Blocks) {
        if (opened()) return

        open()

        coordinate.getAround(blocks.coordinate).forEach {
            val block = blocks.getOne(it)

            if (block is BlankBlock) {
                block.chord(blocks)
            } else {
                block.open()
            }
        }
    }
}
