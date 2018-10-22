package com.fairviewcodeclub.snek.logic

/**
 * A class that represents a digit of a Snek
 */
class SnekBlock(var location: BoardPosition, val color: SnekColor) {
    internal var nextBlock: SnekBlock? = null
    internal var isValid = false
    internal fun move(newLocation: BoardPosition) {
        if (!this.isValid) {
            this.isValid = true
            return
        }
        this.nextBlock?.move(this.location)
        this.location = newLocation
    }
    internal fun grow() {
        if (this.nextBlock == null) {
            this.nextBlock = SnekBlock(this.location, this.color)
            return
        }
        this.nextBlock!!.grow()
    }
}

/**
 * A class that represents a Snek
 */
class Snek(val color: SnekColor) {

    //The actual main block of the snek
    val head = SnekBlock(
            when (this.color) {
                SnekColor.RED -> BoardPosition(0, 0)
                SnekColor.BLUE -> BoardPosition(33, 0)
                SnekColor.GREEN -> BoardPosition(0, 33)
                SnekColor.YELLOW -> BoardPosition(33, 33)
            },
            this.color
    )

    //Whether the snek is alive or not; sneks start as alive
    var isDead = false

    //The space that is occupied by the snek
    val occupiedSpaces: List<BoardPosition>
        get() {
            val allSpaces = mutableListOf<BoardPosition>()
            var current: SnekBlock? = this.head
            while (current != null) {
                current = this.head.nextBlock
            }
            return allSpaces.toList()
        }

    //A variable that keeps track of the snek's score
    var score = 0
        private set(value) {
            field = value
        }

    //The next direction of the snek; 0 means continue current trajectory, 1 means turn right, and -1 means turn left
    var queuedTurn = 0

    /**
     * Initializes the snek by giving it an extra digit
     */
    init {
        this.head.isValid = true
        this.head.grow()
    }

    /**
     * Updates the snek
     */
    fun step(world: World) {
        if (this.isDead) {
            return
        }
        //TODO: if head is on apple, call this.head.grow()
        val currentTrajectory = BoardPosition(this.head.location.row - this.head.nextBlock!!.location.row, this.head.location.col - this.head.nextBlock!!.location.col)
        val newTrajectory = when (this.queuedTurn) {
            0 -> currentTrajectory
            1 -> when (currentTrajectory) {
                BoardPosition(0, 1) -> BoardPosition(1, 0)
                BoardPosition(1, 0) -> BoardPosition(0, -1)
                BoardPosition(0, -1) -> BoardPosition(-1, 0)
                BoardPosition(-1, 0) -> BoardPosition(0, 1)
                else -> BoardPosition(0, 0)
            }
            -1 -> when (currentTrajectory) {
                BoardPosition(0, 1) -> BoardPosition(-1, 0)
                BoardPosition(-1, 0) -> BoardPosition(0, -1)
                BoardPosition(0, -1) -> BoardPosition(1, 0)
                BoardPosition(1, 0) -> BoardPosition(0, 1)
                else -> BoardPosition(0, 0)
            }
            else -> BoardPosition(0, 0)
        }
        this.head.move(BoardPosition(this.head.location.row + newTrajectory.row, this.head.location.col + newTrajectory.col))
        this.queuedTurn = 0
        this.score += this.occupiedSpaces.size
    }

}