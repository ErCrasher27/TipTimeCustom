package com.example.tiptime

enum class CurrencyNames {
    Eur,
    Dollars,
    Pounds
}

class CurrencyExchange {
    object Eur {
        const val currency: Double = 1.0
        const val symbol: String = "€"
    }

    object Dollars {
        const val currency: Double = 0.99
        const val symbol: String = "$"
    }

    object Pounds {
        const val currency: Double = 0.87
        const val symbol: String = "£"
    }
}