package com.fairviewcodeclub.snek

import com.fairviewcodeclub.snek.logic.SnekColor
import com.fairviewcodeclub.snek.logic.TileState
import com.fairviewcodeclub.snek.logic.World
import com.fairviewcodeclub.snek.logic.represent
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * The class that handles managing requests for the competition game
 */
@RestController
@RequestMapping(value=["/api"])
class CompetitionController {

    //The world that the competition is running
    var competitionWorld = World()

    /**
     * Takes in a request to change the direction of the snake
     * Changes the snake that corresponds to the given key
     * 0 means no turn, -1 means a turn left, and 1 means a turn right
     * Returns on what turn the direction was queued (Snek will follow queued direction on the next turn)
     */
    @RequestMapping(method=[RequestMethod.POST])
    fun changeSnekDirection(@RequestParam("turnDirection") turnDirection: Int, @RequestParam("key") key: String): Int {
        val caller = getColorOfKey(key)
        val turnOfCall = this.competitionWorld.numberOfTurns
        if (caller != null && turnDirection >= -1 || turnDirection <= 1) {
            this.competitionWorld.acceptQueueRequest(caller!!, turnDirection)
        }
        return turnOfCall
    }

    /**
     * Returns the game state
     */
    @RequestMapping(method=[RequestMethod.GET])
    fun getBoardState(): Array<Array<TileState>> {
        return represent(this.competitionWorld)
    }

    /**
     * Gets game scores and whether the game is in progress or not
     */
    @RequestMapping(path=["/progress"], method=[RequestMethod.GET])
    fun getGameProgress(): String {
        var outputString = "{"
        fun addValue(key: String, value: String) {
            if (outputString != "{") {
                outputString += ","
            }
            outputString += "\"$key\":$value"
        }
        addValue("done", "${this.competitionWorld.sneks.all { it.isDead }}")
        addValue("turn", "${this.competitionWorld.numberOfTurns}")
        SnekColor.values().forEach { color ->
            val snek = this.competitionWorld.sneks.first { it.color == color }
            addValue(color.toString(), "{\"score\":${snek.score},\"isDead\":${snek.isDead}}")
        }
        return "$outputString}"
    }

}
