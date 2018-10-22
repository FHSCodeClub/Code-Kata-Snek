package com.fairviewcodeclub.snek

import com.fairviewcodeclub.snek.logic.World
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

    var competitionWorld = World()

    /**
     * Takes in a request to change the direction of the snake
     * Changes the snake that corresponds to the given key
     * 0 means no turn, -1 means a turn left, and 1 means a turn right
     */
    @RequestMapping(method=[RequestMethod.POST])
    fun changeSnekDirection(@RequestParam("turnDirection") turnDirection: Int, @RequestParam("key") key: String) {

    }

    /**
     * Returns the game state
     */
    @RequestMapping(method=[RequestMethod.GET])
    fun getBoardState() {

    }

}
