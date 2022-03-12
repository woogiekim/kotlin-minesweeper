package com.minsweeper.exception

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

class MineSweeperException(private val errorCode: ErrorCode) : RuntimeException(errorCode.message) {
    enum class ErrorCode(
        val message: String
    ) {
        OPENED_MINE("지뢰를 밟음"),
        REQUIRE_COORDINATE("좌표는 필수임"),
        OUT_OF_COORDINATE("좌표 범위 벗어남"),
        NOT_GREATER_THAN_ZERO_COORDINATE("좌표는 0보다 커야 함")
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

@OptIn(ExperimentalContracts::class)
inline fun <T : Any> validateNotNull(value: T?, errorCode: () -> MineSweeperException.ErrorCode): T {
    contract {
        returns() implies (value != null)
    }

    if (value == null) {
        throw MineSweeperException(errorCode())
    } else {
        return value
    }
}
