import javax.inject.Inject

class Car @Inject constructor() {
    fun run() {
        println("This is a car")
    }
}