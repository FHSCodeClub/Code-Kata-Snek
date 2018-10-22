package com.fairviewcodeclub.snek.logic

/**
 * Creates a world given the colors in the game
 */
class World(participants: Array<SnekColor> = SnekColor.values()) {

    //The players that the game is waiting on
    val waitingOn = participants.toMutableList()

    //All the sneks in the game
    val sneks = participants.map { Snek(it) }
    //The location of the game's apple
    var appleLocation = BoardPosition(17, 17)

    /**
     * Moves game forward to next state
     */
    fun step() {
        /**
         * Elongates a snek if it's head is on an apple
         */
        val appleEater = this.sneks.firstOrNull { it.head.location == this.appleLocation }
        var changeAppleLocation = false
        if (appleEater != null) {
            appleEater.head.grow()
            changeAppleLocation = true
        }
        /**
         * Makes each snek act
         */
        this.sneks.forEach { it.step() }
        /**
         * Gets snek body locations without the head location
         */
        fun getSnekBody(snek: Snek): List<BoardPosition> {
            return snek.occupiedSpaces.toMutableList().also { it.removeAt(0) }
        }
        /**
         * Kills any sneks that are out of bounds, intersect with their bodies, or intersect with other sneks
         */
        this.sneks.forEach {
            it.isDead = it.head.location.row >= 35
                    || it.head.location.row < 0
                    || it.head.location.col >= 35
                    || it.head.location.col < 0
                    || getSnekBody(it).contains(it.head.location)
                    || this.sneks.any { other -> other.occupiedSpaces.contains(it.head.location) }
        }
        /**
         * Generates a new apple position if the apple is gone
         */
        if (changeAppleLocation) {
            //TODO:
        }
    }

    /**
     * Takes a turn direction for a snake
     */
    fun acceptQueueRequest(sender: SnekColor, turnDirection: Int) {
        this.sneks.first { it.color == sender }.queuedTurn = turnDirection
        if (this.waitingOn.contains(sender)) {
            this.waitingOn.remove(sender)
        }
        if (this.waitingOn.isEmpty()) {
            this.step()
            this.sneks.filter { !it.isDead }.mapTo(this.waitingOn) { it.color }.toMutableList()
        }
    }

}