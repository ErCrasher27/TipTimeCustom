package com.example.tiptime

/**
 * -----------------
 * Tip
 * -----------------
 */

class Tip(
    val costOfService: String,
    val howWasService: Int,
    val roundUp: Boolean,
    val split: Boolean,
    val numberPeople: String,
    private val customPercentage: String,
    val changeCurrency: String,
) {

    private val defaultValueForNull: Int = 0

    val currency: Double
        get() = when (changeCurrency) {
            CurrencyNames.Eur.name -> CurrencyExchange.Eur.currency
            CurrencyNames.Dollars.name -> CurrencyExchange.Dollars.currency
            CurrencyNames.Pounds.name -> CurrencyExchange.Pounds.currency
            else -> CurrencyExchange.Eur.currency
        }

    val symbolCurrency
        get() = when (changeCurrency) {
            CurrencyNames.Eur.name -> CurrencyExchange.Eur.symbol
            CurrencyNames.Dollars.name -> CurrencyExchange.Dollars.symbol
            CurrencyNames.Pounds.name -> CurrencyExchange.Pounds.symbol
            else -> CurrencyExchange.Eur.symbol
        }

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
            this.costOfService.toDouble() * currency
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
