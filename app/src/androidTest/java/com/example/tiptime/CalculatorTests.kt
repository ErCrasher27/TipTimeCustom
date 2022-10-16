package com.example.tiptime

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorTests {

    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calculateTip() {
        compileCostOfServiceEditText(editTextRes = R.string.cost_of_service, cost = "50")
        clickToCalculate(buttonRes = R.string.calculate)

        onView(withId(R.id.tip_result_total))
            .check(matches(withText(containsString("€10.00"))))
    }
}