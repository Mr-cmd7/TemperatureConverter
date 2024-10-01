package com.example.temperatureconverter

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private val scales = arrayOf("Цельсий", "Фаренгейт", "Кельвин")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val spinnerFrom: Spinner = findViewById(R.id.spinnerFrom)
        val spinnerTo: Spinner = findViewById(R.id.spinnerTo)
        val buttonResult: Button = findViewById(R.id.buttonResult)
        val textViewResult: TextView = findViewById(R.id.textViewResult)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, scales)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFrom.adapter = adapter
        spinnerTo.adapter = adapter

        buttonResult.setOnClickListener {
            val input = findViewById<EditText>(R.id.editTextTemperature).text.toString()
            if (input.isEmpty()) {
                Toast.makeText(this, "Пожалуйста, введите температуру", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val temperature = input.toDoubleOrNull()
            if (temperature == null) {
                Toast.makeText(this, "Некорректный ввод", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val fromScale = spinnerFrom.selectedItem.toString()
            val toScale = spinnerTo.selectedItem.toString()

            val result = convertTemperature(temperature, fromScale, toScale)

            textViewResult.text = "Результат: $result $toScale"
        }
    }
    private fun convertTemperature(value: Double, from: String, to: String): Double {
        var tempInCelsius: Double

        tempInCelsius = when (from) {
            "Цельсий" -> value
            "Фаренгейт" -> (value - 32) * 5 / 9
            "Кельвин" -> value - 273.15
            else -> value
        }

        return when (to) {
            "Цельсий" -> tempInCelsius
            "Фаренгейт" -> tempInCelsius * 9 / 5 + 32
            "Кельвин" -> tempInCelsius + 273.15
            else -> tempInCelsius
        }
    }
}