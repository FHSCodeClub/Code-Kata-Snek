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
 * A controller that handles running test environments
 */
@RestController
@RequestMapping(value=["/test/api"])
class TestController {

    //The map of each color's test world to its environment
    val testEnvs = SnekColor.values().map { it to World(arrayOf(it)) }.toMap().toMutableMap()

    /**
     * Takes in a key and creates a test environment for that team
     * If a test environment already exists, it gets replaced/reset with a new one
     */
    @RequestMapping(method=[RequestMethod.POST], params=["key"])
    fun createTestEnv(@RequestParam("key") key: String) {
        val sender = getColorOfKey(key) ?: return
        this.testEnvs[sender] = World(arrayOf(sender))
    }

    /**
     * Takes in a request to change the direction of the snek for the snek in the test environment of the given key
     * Returns the turn on which the direction was queued
     */
    @RequestMapping(method=[RequestMethod.POST], params=["turnDirection", "key"])
    fun testChangeSnekDirection(@RequestParam("turnDirection") turnDirection: Int, @RequestParam("key") key: String): Int {
        val sender = getColorOfKey(key) ?: return -1
        if (turnDirection < -1 || turnDirection > 1) {
            return -1
        }
        val turnOfCall = this.testEnvs[sender]!!.numberOfTurns
        this.testEnvs[sender]!!.acceptQueueRequest(sender, turnDirection)
        return turnOfCall
    }

    /**
     * Returns the game state of the environment matching the given key
     */
    @RequestMapping(method=[RequestMethod.GET])
    fun testGetBoardState(@RequestParam("key") key: String): Array<Array<TileState>> {
        val sender = getColorOfKey(key) ?: return arrayOf()
        return represent(this.testEnvs[sender]!!)
    }

    /**
     * Gets the score and snek details for the test env of the given key
     */
    @RequestMapping(path = ["/progress"], method = [RequestMethod.GET])
    fun getGameProgress(@RequestParam("key") key: String): String {
        val sender = getColorOfKey(key) ?: return ""
        var outputString = "{"
        fun addValue(key: String, value: String) {
            if (outputString != "{") {
                outputString += ","
            }
            outputString += "\"$key\":$value"
        }
        val snek = this.testEnvs[sender]!!.sneks.first()
        addValue("done", "${snek.isDead}")
        addValue("turn", "${this.testEnvs[sender]!!.numberOfTurns}")
        addValue(sender.toString(), "{\"score\":${snek.score},\"isDead\":${snek.isDead},\"hasSubmitted\":false}")
        return "$outputString}"
    }

}