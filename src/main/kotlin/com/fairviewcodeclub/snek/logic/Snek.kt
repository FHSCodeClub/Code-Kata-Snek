package com.fairviewcodeclub.snek.logic

/**
 * A class that represents a digit of a Snek
 */
class SnekBlock(var location: BoardPosition, val color: SnekColor) {
    internal var nextBlock: SnekBlock? = null
    private var isValid = false
    internal fun move(newLocation: BoardPosition) {
        if (!this.isValid) {
            this.isValid = true
            return
        }
        this.nextBlock?.move(this.location)
        this.location = newLocation
    }
    internal fun consumeApple() {
        if (this.nextBlock == null) {
            this.nextBlock = SnekBlock(this.location, this.color)
            return
        }
        this.nextBlock!!.consumeApple()
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

}