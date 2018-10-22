package com.fairviewcodeclub.snek

import com.fairviewcodeclub.snek.logic.SnekColor

/**
 * Converts an API key into a SnekColor
 */
fun getColorOfKey(key: String): SnekColor? {
    return when (key) {
        System.getenv()["RED_PASSWORD"] -> SnekColor.RED
        System.getenv()["BLUE_PASSWORD"] -> SnekColor.BLUE
        System.getenv()["GREEN_PASSWORD"] -> SnekColor.GREEN
        System.getenv()["YELLOW_PASSWORD"] -> SnekColor.YELLOW
        else -> null
    }
}