
package com.minsweeper.exception

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

class MineSweeperException(errorCode: ErrorCode) : RuntimeException(errorCode.message) {
    enum class ErrorCode(
        val message: String
    ) {
        OPENED_MINE("지뢰를 밟음")
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