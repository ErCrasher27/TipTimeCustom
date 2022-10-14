package com.example.tiptime

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding

/**
 *  -----------------
 * MainActivity
 *  -----------------
 */

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //init tipResults (ui)
        binding.tipResultTotal.text = getString(R.string.tip_amount_total, "€0.00")
        binding.tipResultPerPerson.text = getString(R.string.tip_amount_per_person, "€0.00")

        //set OnClickListener on splitSwitch
        binding.splitSwitch.setOnClickListener {
            binding.howManyPeople.isEnabled = !binding.howManyPeople.isEnabled
            binding.howMuchPeopleEditText.text = null
        }

        //set eventClick on calculateButton
        binding.calculateButton.setOnClickListener {
            val tipObj =
                Tip(
                    costOfService = binding.costOfServiceEditText.text.toString(),
                    howWasService = binding.tipOptions.checkedRadioButtonId,
                    roundUp = binding.roundUpSwitch.isChecked,
                    split = binding.splitSwitch.isChecked,
                    numberPeople = binding.howMuchPeopleEditText.text.toString()
                )

            val tip = tipObj.calculateTip()
            val formattedTip = String.format("€%.2f", tip)
            binding.tipResultTotal.text = getString(R.string.tip_amount_total, formattedTip)

            val tipPerPerson = tipObj.calculateTipForPerson()
            val formattedTipPerPerson = String.format("€%.2f", tipPerPerson)
            binding.tipResultPerPerson.text =
                getString(R.string.tip_amount_per_person, formattedTipPerPerson)
        }

        //set OnKeyListener on costOfServiceEditText
        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(view, keyCode)
        }

    }

    /**
     *  -----------------
     * handleKeyEvent
     *  -----------------
     */
    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}


