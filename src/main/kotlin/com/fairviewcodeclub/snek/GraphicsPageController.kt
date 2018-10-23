package com.fairviewcodeclub.snek

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 * The controller that serves a page that graphically shows a world
 */
@Controller
class GraphicsPageController {

    /**
     * Gets the main page regardless of query parameter
     */
    @RequestMapping("/")
    fun mainPage(@RequestParam("key") key: String?): String {
        return "index"
    }

    /**
     * Gets the main page for a test environment regardless of query parameter
     */
    @RequestMapping("/test")
    fun testEnvPage(@RequestParam("key") key: String?): String {
        return "index"
    }

}