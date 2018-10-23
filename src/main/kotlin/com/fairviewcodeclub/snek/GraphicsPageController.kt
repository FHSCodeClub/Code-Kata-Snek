package com.fairviewcodeclub.snek

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 * The controler that serves a page that has graphically shows a world
 */
@Controller
class GraphicsPageController {

    /**
     * Gets the main page regardless of query parameter
     */
    @RequestMapping("/")
    fun mainPage(@RequestParam(value = "key") key: String?): String {
        return "index"
    }

    /**
     * Gets the main page for a test environment regardless of query parameter
     */
    @RequestMapping("/test")
    fun testEnvPage(@RequestParam(value = "key") key: String?): String {
        return "index"
    }

}