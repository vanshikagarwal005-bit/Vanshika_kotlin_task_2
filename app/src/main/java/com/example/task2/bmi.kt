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
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import android.content.Context


class bmi : AppCompatActivity() {

    private val history = mutableListOf<String>()
    private lateinit var historyAdapter: ArrayAdapter<String>
    private lateinit var historyList: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bmi)

        val spinner3 = findViewById<Spinner>(R.id.weightsp)
        val spinner4 = findViewById<Spinner>(R.id.heightsp)
        val weightInput = findViewById<EditText>(R.id.typeWeight)
        val heightInput = findViewById<EditText>(R.id.typeHeight)
        val calculateButton = findViewById<Button>(R.id.button2)
        val historyList = findViewById<ListView>(R.id.historyList)


        val unitWeight = arrayOf("kg", "g")
        val unitHeight = arrayOf("m", "cm")

        val result=findViewById<TextView>(R.id.textView9)
        val description=findViewById<TextView>(R.id.bmiDescription)
        val categoryText=findViewById<TextView>(R.id.bmiCategory)


        spinner3.adapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_dropdown_item, unitWeight
        )
        spinner4.adapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_dropdown_item, unitHeight
        )


        history.addAll(loadHistory())
        historyAdapter = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, history
        )
        historyList.adapter = historyAdapter

        calculateButton.setOnClickListener {
            val weight = weightInput.text.toString().toDoubleOrNull()
            val height = heightInput.text.toString().toDoubleOrNull()

            if (weight == null || height == null || weight <= 0 || height <= 0) {
                Toast.makeText(this, "Enter valid weight and height", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val weightUnit = spinner3.selectedItem.toString()
            val heightUnit = spinner4.selectedItem.toString()

            var weightKg=weight
            if(weightUnit=="g"){
                weightKg=weight/1000
            }
            else{
                weightKg=weight
            }

            var heightM=height
            if(heightUnit=="cm"){
                heightM=height/100
            }
            else{
                heightM=height
            }

            val bmiValue = weightKg / (heightM * heightM)
            val bmiText = String.format("%.1f", bmiValue)

            val (category, message) = getCategory(bmiValue)

            result.text=bmiText
            description.text=message
            categoryText.text=category





            AlertDialog.Builder(this)
                .setTitle("Your BMI: $bmiText")
                .setMessage("Category: $category\n\n$message")
                .setPositiveButton("OK", null)
                .show()


            val entry = "BMI $bmiText - $category  ($weight $weightUnit, $height $heightUnit)"
            history.add(0, entry)
            if (history.size > 50) history.removeAt(history.size - 1)
            historyAdapter.notifyDataSetChanged()
            saveHistory()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getCategory(bmi: Double): Pair<String, String> = when {
        bmi < 18.5 -> "Underweight" to
                "You are below the healthy range. Consider a balanced diet with enough calories and nutrients."
        bmi < 25.0 -> "Normal" to
                "Great job! Your weight is in the healthy range. Keep up your habits."
        bmi < 30.0 -> "Overweight" to
                "You are slightly above the healthy range. Regular exercise and mindful eating can help."
        else -> "Obese" to
                "Your BMI is well above the healthy range. Consider talking to a doctor or dietitian."
    }


    private fun saveHistory() {
        getSharedPreferences("bmi_prefs", Context.MODE_PRIVATE)
            .edit()
            .putString("history", history.joinToString("\n"))
            .apply()
    }

    private fun loadHistory(): List<String> {
        val saved = getSharedPreferences("bmi_prefs", Context.MODE_PRIVATE)
            .getString("history", "") ?: ""
        return if (saved.isEmpty()) emptyList() else saved.split("\n")
    }
}