package com.fairviewcodeclub.snek

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

    /**
     * Takes in a key and creates a test environment for that team
     * If a test environment already exists, it gets replaced/reset with a new one
     */
    @RequestMapping(method=[RequestMethod.POST], params=["key"])
    fun createTestEnv(@RequestParam(value="key", defaultValue="") key: String) {

    }

    /**
     * Takes in a request to change the direction of the snake for the snake in the test environment of the given key
     */
    @RequestMapping(method=[RequestMethod.POST], params=["turnDirection", "key"])
    fun testChangeSnekDirection(@RequestParam(value="turnDirection") turnDirection: Int, @RequestParam(value="key", defaultValue="") key: String) {

    }

    /**
     * Returns the game state of the environment matching the given key
     */
    @RequestMapping(method=[RequestMethod.GET])
    fun testGetBoardState(@RequestParam(value="key", defaultValue="") key: String) {

    }

}