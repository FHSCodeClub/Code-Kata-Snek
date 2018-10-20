enum class Tile {
    NONE, APPLE, SNAKE
}

enum class Player {
    RED, GREEN, BLUE, YELLOW
}

class Coordinate(val x, val y, var tile: Tile)

class World(val size) {

    tiles = Array<Tile>(this.size) { y -> Array<Coordinate>(this.size) { x -> Coordinate(x, y, NONE) } }

    fun getWorldAsJson(): String {
        return 0
    }

    fun receivePlayerInput(player: Player, turn: Int): Boolean {
        return false
    }

    fun iterateStep() {
    }



}