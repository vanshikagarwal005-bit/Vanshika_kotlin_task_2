package com.example.task2


import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Spinner
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    private lateinit var textView5: Spinner
    private lateinit var textView6: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)





        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val spinner1 = findViewById<Spinner>(R.id.textView6)
        val spinner2 = findViewById<Spinner>(R.id.textView5)
        val button = findViewById<Button>(R.id.button)
        val textView9 = findViewById<TextView>(R.id.textView9)


        val currencies1 = arrayOf("Rupees", "Dollar", "Dirham",)
        val currencies2= arrayOf("Rupees", "Dollar", "Dirham",)

        val adapter1 = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            currencies1
        )
        spinner1.adapter = adapter1

        val adapter2 = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            currencies2
        )
        spinner2.adapter = adapter2

        button.setOnClickListener {

            val fromCurrency = spinner1.selectedItem.toString()
            val toCurrency = spinner2.selectedItem.toString()
            val amount = findViewById<EditText>(R.id.editTextText2)
            val input=amount.text.toString()
            val value=input.toDouble()
            var result=value

            if (fromCurrency == "Rupees" && toCurrency == "Dollar") {
                result = value / 83.5
            }
            else if (fromCurrency == "Dollar" && toCurrency == "Rupees") {
                result = value * 83.5
            }
            else if (fromCurrency == "Rupees" && toCurrency == "Dirham") {
                result = value / 22.7
            }
            else if (fromCurrency == "Dirham" && toCurrency == "Rupees") {
                result = value * 22.7
            }
            else if (fromCurrency == "Dollar" && toCurrency == "Dirham") {
                result = value * 3.67
            }
            else if (fromCurrency == "Dirham" && toCurrency == "Dollar") {
                result = value / 3.67
            }

            textView9.text = "$result $toCurrency"


        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}