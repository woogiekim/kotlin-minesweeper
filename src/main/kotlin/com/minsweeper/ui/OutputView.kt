package com.minsweeper.ui

import com.minsweeper.board.Board
import com.minsweeper.game.Command
import com.minsweeper.game.GameStatus

object OutputView {
    fun printBoardCoordinate() {
        println("지뢰찾기 보드의 형태를 입력해주세요. 예) 3,3")
    }

    fun printBoard(board: Board) {
        println(board.display())
    }

    fun printChooseCoordinate() {
        println("좌표를 선택해주세요. 예) 1,1")
    }

    fun printCommand() {
        println("명령을 선택해주세요.")
        println(Command.display())
    }

    fun printAfterClear() {
        println("게임을 클리어 했습니다.")
        println(GameStatus.clearDisplay())
    }

    fun printNewLine() {
        println()
    }
}