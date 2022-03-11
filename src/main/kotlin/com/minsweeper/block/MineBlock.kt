package com.minsweeper.block

import com.minsweeper.exception.MineSweeperException.ErrorCode.OPENED_MINE
import com.minsweeper.exception.validate

class MineBlock(
    coordinate: Coordinate,
    display: String = "💣"
) : SimpleBlock(coordinate, display) {
    override fun open() = validate(false) { OPENED_MINE }
}
