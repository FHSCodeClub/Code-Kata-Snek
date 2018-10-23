package com.fairviewcodeclub.snek.logic

/**
 * An enumeration of all the states a tile can be in
 */
enum class TileState {
    RED_SNEK, GREEN_SNEK, BLUE_SNEK, YELLOW_SNEK, APPLE, EMPTY
}

/**
 * Converts a world to 2d array of tile states
 */
fun represent(world: World): Array<Array<TileState>> {
    return Array(35) { row ->
        Array(35) { col ->
            val currentSneks = world.sneks.filter { it.occupiedSpaces.contains(BoardPosition(row, col)) }
            when {
                currentSneks.isNotEmpty() -> when ((currentSneks.firstOrNull { !it.isDead }
                        ?: currentSneks.first()).color) {
                    SnekColor.RED -> TileState.RED_SNEK
                    SnekColor.BLUE -> TileState.BLUE_SNEK
                    SnekColor.GREEN -> TileState.GREEN_SNEK
                    SnekColor.YELLOW -> TileState.YELLOW_SNEK
                }
                world.appleLocation == BoardPosition(row, col) -> TileState.APPLE
                else -> TileState.EMPTY
            }
        }
    }
}