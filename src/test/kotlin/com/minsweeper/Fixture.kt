package com.minsweeper

import com.minsweeper.block.BlankBlock
import com.minsweeper.block.Coordinate
import com.minsweeper.block.MineBlock
import com.minsweeper.block.NumberBlock

fun blankBlock(
    coordinate: Coordinate = Coordinate(0, 0)
): BlankBlock = BlankBlock(coordinate)

fun numberBlock(
    number: Int = 1,
    coordinate: Coordinate = Coordinate(0, 1)
): NumberBlock = NumberBlock(number, coordinate)

fun mineBlock(
    coordinate: Coordinate = Coordinate(0, 2)
): MineBlock = MineBlock(coordinate)
