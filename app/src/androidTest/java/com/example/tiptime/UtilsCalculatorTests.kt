package com.example.tiptime

import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Test

/** Follow this pattern -> $10.00 for cost */
@Test
fun compileCostOfServiceEditText(@StringRes editTextRes: Int, cost: String) {
    onView(withId(editTextRes))
        .perform(ViewActions.typeText(cost))
}

@Test
fun clickToCalculate(@StringRes buttonRes: Int) {
    onView(withId(buttonRes))
        .perform(ViewActions.scrollTo())
        .perform(ViewActions.click())
}