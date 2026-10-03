package com.example.task2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class choice : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.choice)

        val CurrencyConverter = findViewById<LinearLayout>(R.id.button3)

        CurrencyConverter.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }

            val bmiCalculator = findViewById<LinearLayout>(R.id.button4)

            bmiCalculator.setOnClickListener {

                val intent = Intent(this, bmi::class.java)
                startActivity(intent)
                finish()

            }

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val burger = findViewById<TextView>(R.id.burger)
        val navigationView = findViewById<NavigationView>(R.id.navigationView)

        burger.setOnClickListener {
            drawerLayout.openDrawer(navigationView)
        }

        burger.setOnClickListener {
            drawerLayout.openDrawer(navigationView)
        }


        navigationView.setNavigationItemSelectedListener { item ->

            if (item.itemId == R.id.nav_history) {

                val intent = Intent(this, history::class.java)
                startActivity(intent)

            }

            true
        }


                ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                    v.setPadding(
                        systemBars.left,
                        systemBars.top,
                        systemBars.right,
                        systemBars.bottom
                    )
                    insets
                }
            }
        }
