package com.example.tiptime

/**
 * -----------------
 * Tip
 * ChangeCurrency if true = "€", if false = "$"
 * -----------------
 */
class Tip(
    val costOfService: String,
    val howWasService: Int,
    val roundUp: Boolean,
    val split: Boolean,
    val numberPeople: String,
    private val customPercentage: String,
    val changeCurrency: Boolean,
) {

    private val defaultValueForNull: Int = 0

    private val euroValue = 1.03

    private val tipPercentage: Int
        get() = when (this.howWasService) {
            R.id.option_twenty_percent -> 20
            R.id.option_eighteen_percent -> 18
            R.id.option_custom -> {
                if (customPercentage.isNotEmpty()) {
                    customPercentage.toInt()
                } else {
                    defaultValueForNull
                }
            }
            else -> 15

        }

    private val cost: Double
        get() = if (this.costOfService.isNotEmpty()) {
            if (changeCurrency) {
                this.costOfService.toDouble()
            } else {
                this.costOfService.toDouble() * euroValue
            }
        } else {
            0.0
        }

    fun calculateTip(): Double {
        return if (roundUp) {
            kotlin.math.ceil(cost / 100 * tipPercentage)
        } else {
            cost / 100 * tipPercentage
        }
    }

    fun calculateTipForPerson(): Double {
        return if (split && numberPeople.isNotEmpty()) {
            calculateTip() / numberPeople.toInt()
        } else {
            calculateTip()
        }
    }

    fun totalAmount(): Double {
        return calculateTip() + cost
    }
}
