package com.fairviewcodeclub.snek.logic

/**
 * Creates a world given the colors in the game
 */
class World(participants: Array<SnekColor> = SnekColor.values()) {

    //All the sneks in the game
    val sneks = participants.map { Snek(it) }

}