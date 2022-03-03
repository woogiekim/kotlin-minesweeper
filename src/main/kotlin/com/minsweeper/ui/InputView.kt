package com.minsweeper.ui

import com.minsweeper.block.Coordinate

object InputView {

    fun readBoardCoordinate(): Coordinate {
        OutputView.printBoardCoordinate()

        val readLine = readLine()?.split(",", limit = 2)?.map { it.toInt() }

        return readLine?.let { Coordinate(readLine[0] to readLine[1]) } ?: Coordinate()
    }
}