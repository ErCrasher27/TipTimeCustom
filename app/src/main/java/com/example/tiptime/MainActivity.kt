package com.example.tiptime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            val tip =
                Tip(
                    costOfService = binding.costOfService.text.toString(),
                    howWasService = binding.tipOptions.checkedRadioButtonId,
                    roundUp = binding.roundUpSwitch.isChecked
                )
            binding.tipResult.text = tip.calculateTip().toString()
        }
    }
}


