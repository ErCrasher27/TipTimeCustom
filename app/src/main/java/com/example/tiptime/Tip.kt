package com.example.tiptime

/**
 * -----------------
 * CALCULATE TIP
 * -----------------
 */
class Tip(
    var costOfService: String,
    var howWasService: Int,
    var roundUp: Boolean,
) {
    private val tipPercentage: Int
        get() = when (this.howWasService) {
            R.id.option_twenty_percent -> 20
            R.id.option_eighteen_percent -> 18
            else -> 15
        }

    private val cost: Double
        get() = if (costOfService.isNotEmpty()) {
            costOfService.toDouble()
        } else {
            0.0
        }

    fun calculateTip(): Double {
        return if (roundUp) {
            kotlin.math.ceil(cost / 100 * tipPercentage)
        } else {
            cost / 100 * tipPercentage
            //String.format("%.2f", cost / 100 * tipPercentage)
        }
    }
}
