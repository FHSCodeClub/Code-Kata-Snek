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
        this.nextBlock?.move(BoardPosition(this.location.row, this.location.col))
        this.location = newLocation
    }
    fun grow() {
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
                SnekColor.RED -> BoardPosition(0, 1)
                SnekColor.BLUE -> BoardPosition(33, 0)
                SnekColor.GREEN -> BoardPosition(1, 34)
                SnekColor.YELLOW -> BoardPosition(34, 33)
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
                allSpaces.add(current.location)
                current = current.nextBlock
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
        this.head.nextBlock!!.location = when (this.color) {
            SnekColor.RED -> BoardPosition(0, 0)
            SnekColor.BLUE -> BoardPosition(34, 0)
            SnekColor.GREEN -> BoardPosition(0, 34)
            SnekColor.YELLOW -> BoardPosition(34, 34)
        }
        this.head.nextBlock!!.isValid = true
    }

    /**
     * Updates the snek
     */
    fun step() {
        if (this.isDead) {
            return
        }
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