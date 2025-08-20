package simplecar
import kotlin.math.max
import kotlin.math.min

class Car(val maxSpeed: Double = 120.0, val gasolineCapacity: Double = 50.0) {
    var gasolineLevel: Double = 0.0
        private set
    var speed: Double = 0.0
        private set
    fun fillTank() {
        gasolineLevel = gasolineCapacity
    }
    fun accelerate() {
        val newSpeed = min(speed + 1.0, maxSpeed)
        val gasolineNeeded = (newSpeed - speed) * 0.1
        if(gasolineLevel >= gasolineNeeded) {
            gasolineLevel -= gasolineNeeded
            speed = newSpeed
        }
    }
    fun decelerate() {
        speed = max(speed - 1.5, 0.0)
    }
}
