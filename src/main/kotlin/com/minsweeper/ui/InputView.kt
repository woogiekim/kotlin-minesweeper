package com.minsweeper.ui

import com.minsweeper.block.Coordinate
import com.minsweeper.game.Command
import com.minsweeper.game.GameStatus

object InputView {

    fun readBoardCoordinate(): Coordinate {
        OutputView.printBoardCoordinate()

        return readCoordinate()
    }

    fun readBlockCoordinate(): Coordinate {
        OutputView.printChooseCoordinate()

        return readCoordinate()
    }

    fun readChooseCommand(): Command {
        OutputView.printCommand()

        return Command.of(readLine()?.toInt()!!)
    }

    fun readAfterClear(): GameStatus {
        OutputView.printAfterClear()

        return GameStatus.clearOf(readLine()?.toInt()!!)
    }

    private fun readCoordinate(): Coordinate {
        val readLine = readLine()?.split(",", limit = 2)?.map { it.toInt() }

        return readLine?.let { Coordinate(readLine[0] to readLine[1]) } ?: Coordinate()
    }
}