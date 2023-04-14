package pt.isel

import kotlin.math.sqrt

class Point(val x: Int, val y: Int) {
    val module get() = sqrt(x.toDouble()*x + y*y)
}