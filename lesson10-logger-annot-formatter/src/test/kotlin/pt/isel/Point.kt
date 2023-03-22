package pt.isel

import kotlin.math.sqrt

class Point(@get:NonLog var x: Int,  @get:NonLog var y: Int) {
    @get:AltName(name="MODULE") val module get() = sqrt(x.toDouble()*x + y*y)
}