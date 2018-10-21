enum class Tile {
    NONE, APPLE, SNAKE
}

enum class Player {
    RED, GREEN, BLUE, YELLOW
}

enum class Direction {
    UP, DOWN, LEFT, RIGHT
}

class Coordinate(val x: Int, val y: Int, var tile: Tile)

class World(val size: Int) {

    var tiles = Array<Array<Coordinate>>(this.size) { y -> Array<Coordinate>(this.size) { x -> Coordinate(x, y, Tile.NONE) } }
    var waitingOn = Player.values().toMutableList()
    var snekDirections = mutableMapOf(Player.RED to Direction.UP, Player.GREEN to Direction.DOWN, Player.BLUE to Direction.LEFT, Player.YELLOW to Direction.RIGHT)
    var snekLocations = mutableMapOf(Player.RED to Pair(this.size - 1, 0), Player.GREEN to Pair(0, this.size - 1), Player.BLUE to Pair(this.size - 1, this.size - 1), Player.YELLOW to Pair(0, 0))

    init {
        this.tiles[0][0].tile = Tile.SNAKE
        this.tiles[0][this.size - 1].tile = Tile.SNAKE
        this.tiles[this.size - 1][0].tile = Tile.SNAKE
        this.tiles[this.size - 1][this.size - 1].tile = Tile.SNAKE
    }

    fun getWorldAsJson(): String {
        return "0"
    }

    fun receivePlayerInput(player: Player, turn: Int): Boolean {
        if (!this.waitingOn.contains(player)) {
            return false
        }
        this.waitingOn.remove(player)
        //change snek direction
        return true
    }

    fun iterateStep() {
        /*if (!this.tiles.flatten().any { it.tile == Tile.APPLE } ) {
            this.tiles.flatten().filter { it.tile != Tile.SNAKE }.maxBy {
                this.snekLocations.sumBy { coord -> Math.abs(coord.second() - it.x) + Math.abs(coord.first() - it.y) }
            }.tile = Tile.APPLE
        }*/
        for (i in 0 until 4) {
            //Move sneks
        }
    }



}