package org.example

// --- Subject interface ---
interface Subject {
    fun registerObserver(observer: Observer)
    fun removeObserver(observer: Observer)
    fun notifyObservers()
}

// --- Observer interface ---
interface Observer {
    fun update(data: WeatherData)
}

// --- Data class for measurements ---
data class WeatherData(val temperature: Float, val humidity: Float, val pressure: Float)

// --- The Subject (or Observable) ---
class WeatherStation: Subject {
    private val observers = mutableListOf<Observer>()
    private var currentData: WeatherData? = null

    override fun registerObserver(observer: Observer) {
        observers.add(observer)
    }

    override fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

    override fun notifyObservers() {
        currentData?.let { data ->
            for (observer in observers) {
                observer.update(data)
            }
        }
    }

    // This method is called whenever new weather data is available.
    fun measurementsChanged(newData: WeatherData) {
        this.currentData = newData
        println("WeatherStation: Got new data -> $currentData")
        notifyObservers()
    }
}

// --- The Observers ---
// This class updates its display when it receives new data.
class CurrentConditionsDisplay: Observer {
    private var temperature: Float = 0.0f
    private var humidity: Float = 0.0f

    override fun update(data: WeatherData) {
        this.temperature = data.temperature
        this.humidity = data.humidity
        display()
    }

    fun display() {
        // Prints the current temperature and humidity.
        println("CurrentConditionsDisplay: Current conditions: ${temperature}C degrees and $humidity% humidity")
    }
}

// This class calculates and displays the average temperature.
class StatisticsDisplay: Observer {
    private val temperatures = mutableListOf<Float>()

    override fun update(data: WeatherData) {
        temperatures.add(data.temperature)
        display()
    }

    fun display() {
        // Calculates the average and print it.
        println("StatisticsDisplay: Avg temperature: ${temperatures.average()}C")
    }
}

// --- Main function to run the simulation ---
fun main() {
    // 1. Create the WeatherStation (the subject).
    val weatherStation = WeatherStation()
    // 2. Create the display devices (the observers).
    val currentDisplay = CurrentConditionsDisplay()
    val statsDisplay = StatisticsDisplay()
    // 3. Register the observers with the weather station.
    weatherStation.registerObserver(currentDisplay)
    weatherStation.registerObserver(statsDisplay)
    // Simulate new weather measurements.
    println("--- Simulating new measurement ---")
    weatherStation.measurementsChanged(WeatherData(25.0f, 65f, 1012f))
    println("\n--- Simulating another measurement ---")
    weatherStation.measurementsChanged(WeatherData(27.5f, 70f, 1011f))
    // 4. Unregister one of the observers.
    println("\n--- Unregistering Statistics Display ---")
    weatherStation.removeObserver(statsDisplay)
    println("\n--- Simulating a final measurement ---")
    weatherStation.measurementsChanged(WeatherData(26.0f, 90f, 1013f))
}
