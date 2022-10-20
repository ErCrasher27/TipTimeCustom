package com.example.tiptime

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.SummaryPageBinding

class SummaryPage : AppCompatActivity(){

    private lateinit var binding: SummaryPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = SummaryPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            val intent = Intent(this@SummaryPage, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun text() {
        val total = intent.getStringExtra("total")
        val tip = intent.getStringExtra("tip")
        val tipPerson = intent.getStringExtra("tipPerson")

        binding.total.text = total
        binding.tipResult.text = tip
        binding.tipPerPersonResult.text = tipPerson
    }
}