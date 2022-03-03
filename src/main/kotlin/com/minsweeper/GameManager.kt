package com.minsweeper

import com.minsweeper.ui.InputView

class GameManager : Runnable {

    override fun run() {
    }

    fun startGame() {
        while (GameStatus.START) {
        }
    }
}

enum class GameStatus {
    READY, START, RESTART, END
}

object GameContext {
    var gameStatus: GameStatus = GameStatus.READY
}