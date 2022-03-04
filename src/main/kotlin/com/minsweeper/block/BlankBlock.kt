package com.minsweeper.block

class BlankBlock(
    val coordinate: Coordinate,
    display: String = "  "
) : SimpleBlock(coordinate, display)
