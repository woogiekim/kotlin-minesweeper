package com.minsweeper.game

import com.minsweeper.block.Blocks
import com.minsweeper.block.Coordinate
import com.minsweeper.board.Board
import com.minsweeper.component.BlockGenerator
import com.minsweeper.component.MineGenerator
import com.minsweeper.component.NumberAllocator
import com.minsweeper.exception.MineSweeperException
import com.minsweeper.ui.InputView
import com.minsweeper.ui.OutputView

class GameManager(
    private val blockGenerator: BlockGenerator,
    private val mineGenerator: MineGenerator,
    private val numberAllocator: NumberAllocator
) {
    init {
        GameContext.start()
    }

    fun start() {
        do {
            init()
        } while (GameContext.canStart())
    }

    private fun init() {
        var board: Board? = null

        try {
            board = makeBoard()

            sweepMine(board)
        } catch (e: MineSweeperException) {
            OutputView.printErrorMessage(e)
            OutputView.printBoardDisplay(board)
        } catch (e: RuntimeException) {
            OutputView.printErrorMessage(e)
            OutputView.printGameEndDisplay(board)
        }
    }

    private fun makeBoard(): Board {
        val coordinate = InputView.readBoardCoordinate()
        val blocks = initBlocks(coordinate)
        val board = Board(blocks)

        OutputView.printBoardDisplay(board)
        OutputView.printNewLine()

        return board
    }

    private fun initBlocks(coordinate: Coordinate): Blocks {
        val blocks = Blocks.create(coordinate, blockGenerator)
        val minedBlocks = mineGenerator.generate(blocks)

        return numberAllocator.allocate(minedBlocks)
    }

    private fun sweepMine(board: Board) {
        do {
            try {
                val targetCoordinate = InputView.readBlockCoordinate()
                OutputView.printNewLine()

                val block = board.getBlock(targetCoordinate)
                val command = InputView.readChooseCommand()
                OutputView.printNewLine()

                command.action(block)

                OutputView.printBoardDisplay(board)
                OutputView.printNewLine()

                if (board.clear()) GameContext.clear()
            } catch (e: MineSweeperException) {
                OutputView.printErrorMessage(e)
            } catch (e: RuntimeException) {
                throw e
            }
        } while (!GameContext.isClear())

        clearAndThen()
    }

    private fun clearAndThen() {
        when (InputView.readAfterClear()) {
            GameStatus.RESTART -> GameContext.restart()
            GameStatus.END -> GameContext.end()
            else -> throw IllegalArgumentException("보기에 없는 명령")
        }
    }
}
