package com.example.task2

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import android.widget.EditText
import android.widget.Button

class bmi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bmi)

        val spinner3 = findViewById<Spinner>(R.id.weightsp)
        val spinner4 = findViewById<Spinner>(R.id.heightsp)
        val weightInput = findViewById<EditText>(R.id.typeWeight)
        val heightInput = findViewById<EditText>(R.id.typeHeight)
        val calculateButton = findViewById<Button>(R.id.button2)
        val answer = findViewById<TextView>(R.id.textView9)


        val unitWeight = arrayOf("kg", "g",)
        val unitHeight= arrayOf("m", "cm",)

        val adapter1 = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            unitWeight
        )
        spinner3.adapter = adapter1

        val adapter2 = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            unitHeight
        )
        spinner4.adapter = adapter2


        calculateButton.setOnClickListener {
            val weight = weightInput.text.toString().toDouble()
            val height = heightInput.text.toString().toDouble()

            val selectedWeightUnit = spinner3.selectedItem.toString()
            val selectedHeightUnit = spinner4.selectedItem.toString()

            var weightKg = weight
            var heightM = height

            if (selectedWeightUnit == "g") {
                weightKg = weight / 1000
            }
            if (selectedHeightUnit == "cm") {
                heightM = height / 100
            }
            val bmi = weightKg / (heightM * heightM)
            answer.text = bmi.toString()

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}