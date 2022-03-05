package com.minsweeper.game

import java.util.*

enum class GameStatus {
    READY,
    START,
    RESTART,
    CLEAR,
    END;

    companion object {
        private val AFTER_CLEAR = EnumSet.of(RESTART, END)

        fun afterClear(code: Int): GameStatus {
            return AFTER_CLEAR.filterIndexed { index, _ -> index + 1 == code }.first()
        }

        fun clearDisplay(): String {
            return AFTER_CLEAR.mapIndexed { index, status -> (index + 1) to status }
                .joinToString(System.lineSeparator()) { "${it.first}. ${it.second}" }
        }
    }
}
