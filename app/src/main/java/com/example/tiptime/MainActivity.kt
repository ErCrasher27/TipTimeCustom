package com.example.tiptime

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.RadioButton
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
        if (intent.getDoubleExtra("cost", 0.0) != 0.0) {
            binding.costOfService.editText?.setText(intent.getDoubleExtra("cost", 0.0).toString())
        }
        var symbolCurrency = "€"

        //set OnClickListener on splitSwitch
        binding.splitSwitch.setOnClickListener {
            binding.howManyPeople.isEnabled = !binding.howManyPeople.isEnabled
            binding.howMuchPeopleEditText.text = null
        }

        //set OnClickListener on changeCurrencySwitch
        binding.changeCurrencySwitch.setOnClickListener {
            symbolCurrency = if (symbolCurrency == "€")
                "$" else {
                "€"
            }
        }

        //set OnCheckedChangeListener on tipOption
        binding.tipOptions.setOnCheckedChangeListener { group, checkedId ->
            val radio: RadioButton = group.findViewById(checkedId)
            if (radio == binding.optionCustom) {
                binding.optionCustomTextInput.isEnabled = true
                binding.optionCustomEditText.text = null
            } else {
                binding.optionCustomTextInput.isEnabled = false
                binding.optionCustomEditText.text = null
            }
        }

        //set eventClick on calculateButton
        binding.calculateButton.setOnClickListener {
            calculate(symbolCurrency)
        }

        //set OnKeyListener on costOfServiceEditText
        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(view, keyCode)
        }

    }


    private fun calculate(symbolCurrency : String){
        val tipObj =
            Tip(
                costOfService = binding.costOfServiceEditText.text.toString(),
                howWasService = binding.tipOptions.checkedRadioButtonId,
                roundUp = binding.roundUpSwitch.isChecked,
                split = binding.splitSwitch.isChecked,
                numberPeople = binding.howMuchPeopleEditText.text.toString(),
                customPercentage = binding.optionCustomEditText.text.toString(),
                changeCurrency = binding.changeCurrencySwitch.isChecked
            )

        val stringInTextField = binding.costOfServiceEditText.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        val costFormatted = String.format("$symbolCurrency%.2f", cost)

        val tip = tipObj.calculateTip()
        val formattedTip = String.format("$symbolCurrency%.2f", tip)

        val tipPerPerson = tipObj.calculateTipForPerson()
        val formattedTipPerPerson = String.format("$symbolCurrency%.2f", tipPerPerson)

        val totalAmount = tipObj.totalAmount()
        val formattedTotalAmount = String.format("$symbolCurrency%.2f", totalAmount)

        val intent = Intent(this@MainActivity, SummaryPage::class.java)
        intent.putExtra("total", formattedTotalAmount)
        intent.putExtra("tip", formattedTip)
        intent.putExtra("cost", costFormatted)
        intent.putExtra("tipPerson", formattedTipPerPerson)
        startActivity(intent)

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



