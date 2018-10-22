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
fun represent(world: World): String {
    fun representationAtLocation(location: BoardPosition): TileState {
        val currentSneks = world.sneks.filter { it.occupiedSpaces.contains(location) }
        return when {
            currentSneks.isNotEmpty() -> when ((currentSneks.firstOrNull { !it.isDead } ?: currentSneks.first()).color) {
                SnekColor.RED -> TileState.RED_SNEK
                SnekColor.BLUE -> TileState.BLUE_SNEK
                SnekColor.GREEN -> TileState.GREEN_SNEK
                SnekColor.YELLOW -> TileState.YELLOW_SNEK
            }
            world.appleLocation == location -> TileState.APPLE
            else -> TileState.EMPTY
        }
    }
    var representation = "["
    for (rowNum in 0 until 35) {
        representation += "["
        for (colNum in 0 until 35) {
            print("${TileState.EMPTY}")
            representation += "\"${representationAtLocation(BoardPosition(rowNum, colNum))}\""
            if (colNum != 34) {
                representation += ","
            }
        }
        representation += "]"
        if (rowNum != 34) {
            representation += ","
        }
    }
    representation += "]"
    return representation
}