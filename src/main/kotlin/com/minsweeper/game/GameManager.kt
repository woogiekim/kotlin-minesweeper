package com.minsweeper.game

import com.minsweeper.block.Blocks
import com.minsweeper.board.Board
import com.minsweeper.component.BlockGenerator
import com.minsweeper.component.MineGenerator
import com.minsweeper.ui.InputView
import com.minsweeper.ui.OutputView

class GameManager(
    private val blockGenerator: BlockGenerator,
    private val mineGenerator: MineGenerator
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
        startGame@ do {
            val board = makeBoard()

            sweepMine(board)
        } while (GameContext.canStart())
    }

    private fun makeBoard(): Board {
        val coordinate = InputView.readBoardCoordinate()
        val blocks = Blocks.create(coordinate, blockGenerator)

        val board = Board(mineGenerator.generate(blocks))

        OutputView.printBoard(board)
        OutputView.printNewLine()

        return board
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

                OutputView.printBoard(board)
                OutputView.printNewLine()

                clearAndThen(board)
            } catch (e: RuntimeException) {
                println(e.message)
            }
        } while (!GameContext.isEnd())
    }

    private fun clearAndThen(board: Board) {
        if (!board.isClear()) {
            return
        }

        when (InputView.readAfterClear()) {
            GameStatus.RESTART -> GameContext.restart()
            GameStatus.END -> GameContext.end()
            else -> throw IllegalArgumentException("보기에 없는 명령")
        }
    }
}
