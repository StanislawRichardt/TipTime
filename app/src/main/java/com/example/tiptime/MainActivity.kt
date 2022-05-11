package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener{
            val tipAmount = NumberFormat.getCurrencyInstance().format(calculateTip(getTipPercentage()))
            binding.calculatingResult.text = getString(R.string.tip_result_placeholder, tipAmount)
        }
    }

    private fun calculateTip(percentage: Double): Double {
        val tip = binding.costOfServiceEditText.text.toString().toDoubleOrNull()
        return if(tip != null) if(binding.roundingSwitch.isChecked) ceil(tip * percentage) else tip * percentage
        else 0.0
    }

    private fun getTipPercentage(): Double{
        return when {
            binding.amazingOption.isChecked -> 0.20
            binding.goodOption.isChecked -> 0.18
            else -> 0.15
        }
    }
}