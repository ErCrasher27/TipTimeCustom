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

    private val cost: Float
        get() = if (costOfService.isNotEmpty()) {
            costOfService.toFloat()
        } else {
            0.toFloat()
        }

    fun calculateTip(): Any {
        return if (roundUp) {
            kotlin.math.ceil(cost / 100 * tipPercentage).toInt()
        } else {
            String.format("%.1f", cost / 100 * tipPercentage)
        }
    }
}
