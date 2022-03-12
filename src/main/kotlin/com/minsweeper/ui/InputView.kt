package com.minsweeper.ui

import com.minsweeper.block.Coordinate
import com.minsweeper.exception.MineSweeperException.ErrorCode.REQUIRE_COORDINATE
import com.minsweeper.exception.validate
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

        return GameStatus.afterClear(readLine()?.toInt()!!)
    }

    private fun readCoordinate(): Coordinate {
        val readLine = readLine()?.split(",")?.map { it.toInt() }

        validate(readLine != null && readLine.size == 2) { REQUIRE_COORDINATE }

        return Coordinate(readLine[0] to readLine[1])
    }
}
