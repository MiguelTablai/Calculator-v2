package com.example.miguelscalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.graphics.Color
class MainActivity : AppCompatActivity() {

    //For storing the selected math operation
    private var selectedOperation: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //Variables
        val input1 = findViewById<EditText>(R.id.input1)
        val input2 = findViewById<EditText>(R.id.input2)
        val resultText = findViewById<TextView>(R.id.resultText)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnSubtract = findViewById<Button>(R.id.btnSubtract)
        val btnMultiply = findViewById<Button>(R.id.btnMultiply)
        val btnDivide = findViewById<Button>(R.id.btnDivide)
        val btnEqual = findViewById<Button>(R.id.btnEqual)
        val btnReset = findViewById<Button>(R.id.btnReset)

        //Gets both the inputs from the user and pairs them together as a double
        fun getInputs(): Pair<Double?, Double?> {
            val num1 = input1.text.toString().toDoubleOrNull()
            val num2 = input2.text.toString().toDoubleOrNull()
            return Pair(num1, num2)
        }
        // Toast to show various error messages
        fun showError(message: String) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

    //Highlights the selected button and resets the others to default
    fun highlightedSelected(button: Button) {
        val defaultColor = Color.LTGRAY
        val highlightedColor = Color.BLUE

        btnAdd.setBackgroundColor(defaultColor)
        btnSubtract.setBackgroundColor(defaultColor)
        btnMultiply.setBackgroundColor(defaultColor)
        btnDivide.setBackgroundColor(defaultColor)

        button.setBackgroundColor(highlightedColor)
    }

        //Stores operations AND now highlights the selected button
        btnAdd.setOnClickListener {
            selectedOperation = "+"
            highlightedSelected(btnAdd)
        }
        btnSubtract.setOnClickListener {
            selectedOperation = "-"
            highlightedSelected(btnSubtract)
        }
        btnMultiply.setOnClickListener {
            selectedOperation = "*"
            highlightedSelected(btnMultiply)
        }
        btnDivide.setOnClickListener {
            selectedOperation = "/"
            highlightedSelected(btnDivide)
        }

        //Equal button and math function
        btnEqual.setOnClickListener {
            val (num1, num2) = getInputs()
            if (num1 == null || num2 == null) {
                showError("Please enter valid numbers")
                return@setOnClickListener
            }
            when (selectedOperation) {
                "+" -> resultText.text = "Answer: ${num1 + num2}"
                "-" -> resultText.text = "Answer: ${num1 - num2}"
                "*" -> resultText.text = "Answer: ${num1 * num2}"
                "/" -> {
                    if (num2 == 0.0) {
                        showError("Cannot divide by zero")
                    } else {
                        resultText.text = "Answer: ${num1 / num2}"
                    }
                }
                else -> showError("Please select an operation")
            }
        }

        //Reset button AND operation button highlights
        btnReset.setOnClickListener {
            input1.text.clear()
            input2.text.clear()
            resultText.text = "Answer will appear here"
            selectedOperation = null

            val defaultColor = Color.LTGRAY
            btnAdd.setBackgroundColor(defaultColor)
            btnSubtract.setBackgroundColor(defaultColor)
            btnMultiply.setBackgroundColor(defaultColor)
            btnDivide.setBackgroundColor(defaultColor)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}