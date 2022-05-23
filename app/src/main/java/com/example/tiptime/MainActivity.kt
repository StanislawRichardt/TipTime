package com.example.tiptime

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
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

        binding.costOfServiceEditText.setOnKeyListener{view,keyCode,_ -> handleKeyEvent(view,keyCode)}
    }

    private fun calculateTip(percentage: Double): Double {
        val tip = binding.costOfServiceEditText.text.toString().toDoubleOrNull()
        return if(tip != null) if(binding.roundingSwitch.isChecked) ceil(tip * percentage) else tip * percentage
        else 0.0
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean{
        if(keyCode == KeyEvent.KEYCODE_ENTER){
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }

    private fun getTipPercentage(): Double{
        return when {
            binding.amazingOption.isChecked -> 0.20
            binding.goodOption.isChecked -> 0.18
            else -> 0.15
        }
    }
}