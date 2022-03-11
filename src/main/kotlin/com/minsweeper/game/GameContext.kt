package com.minsweeper.game

import com.minsweeper.game.GameStatus.*
import java.util.*

object GameContext {
    private var status: GameStatus = READY

    fun start() {
        changeStatus(START)
    }

    fun restart() {
        changeStatus(RESTART)
    }

    fun clear() {
        changeStatus(CLEAR)
    }

    fun end() {
        changeStatus(END)
    }

    private fun changeStatus(status: GameStatus) {
        this.status = status
    }

    fun canStart(): Boolean = this.status in EnumSet.of(START, RESTART)

    fun isClear(): Boolean = this.status == CLEAR
}
