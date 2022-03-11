
package com.minsweeper.exception

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

class MineSweeperException(private val errorCode: ErrorCode) : RuntimeException(errorCode.message) {
    enum class ErrorCode(
        val message: String
    ) {
        OPENED_MINE("지뢰를 밟음"),
        COORDINATE_MUST_GREATER_THAN_ZERO("보드 좌표는 0보다 커야 함")
    }
}

@OptIn(ExperimentalContracts::class)
inline fun validate(value: Boolean, errorCode: () -> MineSweeperException.ErrorCode) {
    contract {
        returns() implies value
    }

    if (!value) {
        throw MineSweeperException(errorCode())
    }
}
