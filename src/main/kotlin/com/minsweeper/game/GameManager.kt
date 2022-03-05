package com.minsweeper.game

import com.minsweeper.block.Blocks
import com.minsweeper.block.Coordinate
import com.minsweeper.board.Board
import com.minsweeper.component.BlockGenerator
import com.minsweeper.component.MineGenerator
import com.minsweeper.component.NumberAllocator
import com.minsweeper.ui.InputView
import com.minsweeper.ui.OutputView

class GameManager(
    private val blockGenerator: BlockGenerator,
    private val mineGenerator: MineGenerator,
    private val numberAllocator: NumberAllocator
) : Runnable {
    init {
        GameContext.start()
    }

    override fun run() {
        try {
            startGame()
        } catch (e: Exception) {
            println("게임 오류로 종료. 오류: ${e.message}")
        }
    }

    private fun startGame() {
        do {
            var board: Board? = null
            try {
                board = makeBoard()

                sweepMine(board)
            } catch (e: Exception) {
                OutputView.printGameEndDisplay(board!!)
            }
        } while (GameContext.canStart())
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
        val minedBlocks = mineGenerator.generate(blocks).apply { shuffle() }

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

                clearAndThen(board)
            } catch (e: RuntimeException) {
                println(e.message)
            }
        } while (!GameContext.clear())
    }

    private fun clearAndThen(board: Board) {
        if (!board.clear()) {
            return
        }

        GameContext.end()

        when (InputView.readAfterClear()) {
            GameStatus.RESTART -> GameContext.restart()
            GameStatus.END -> GameContext.end()
            else -> throw IllegalArgumentException("보기에 없는 명령")
        }
    }
}
