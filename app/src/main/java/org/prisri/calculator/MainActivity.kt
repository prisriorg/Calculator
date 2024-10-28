package org.prisri.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var input: TextView
    private lateinit var output: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        input= findViewById(R.id.input)
        output = findViewById(R.id.output)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        findViewById<Button>(R.id.button_clear).setOnClickListener {
            input.text = ""
            output.text = ""
        }

        findViewById<Button>(R.id.button_bracket).setOnClickListener {

            input.text = addToInputText("(")

        }
        findViewById<Button>(R.id.button_bracket_r).setOnClickListener {

            input.text = addToInputText(")")

        }

        findViewById<Button>(R.id.button_croxx).setOnClickListener {
            val removedLast = input.text.toString().dropLast(1)
            input.text = removedLast
        }

        findViewById<Button>(R.id.button_0).setOnClickListener {
            input.text = addToInputText("0")
        }
        findViewById<Button>(R.id.button_1).setOnClickListener {
            input.text = addToInputText("1")
        }
        findViewById<Button>(R.id.button_2).setOnClickListener {
            input.text = addToInputText("2")
        }
        findViewById<Button>(R.id.button_3).setOnClickListener {
            input.text = addToInputText("3")
        }
        findViewById<Button>(R.id.button_4).setOnClickListener {
            input.text = addToInputText("4")
        }
        findViewById<Button>(R.id.button_5).setOnClickListener {
            input.text = addToInputText("5")
        }
        findViewById<Button>(R.id.button_6).setOnClickListener {
            input.text = addToInputText("6")
        }
        findViewById<Button>(R.id.button_7).setOnClickListener {
            input.text = addToInputText("7")
        }
        findViewById<Button>(R.id.button_8).setOnClickListener {
            input.text = addToInputText("8")
        }
        findViewById<Button>(R.id.button_9).setOnClickListener {
            input.text = addToInputText("9")
        }
        findViewById<Button>(R.id.button_dot).setOnClickListener {
            input.text = addToInputText(".")
        }
        findViewById<Button>(R.id.button_division).setOnClickListener {
            input.text = addToInputText("÷") // ALT + 0247
        }
        findViewById<Button>(R.id.button_multiply).setOnClickListener {
            input.text = addToInputText("×") // ALT + 0215
        }

        findViewById<Button>(R.id.button_subtraction).setOnClickListener {
            input.text = addToInputText("-")
        }
        findViewById<Button>(R.id.button_addition).setOnClickListener {
            input.text = addToInputText("+")
        }

        findViewById<Button>(R.id.button_equals).setOnClickListener {
            showResult()
        }
    }

    private fun addToInputText(buttonValue: String): String {

        return input.text.toString() + "" + buttonValue
    }

    private fun getInputExpression(): String {
        var expression = input.text.replace(Regex("÷"), "/")
        expression = expression.replace(Regex("×"), "*")
        return expression
    }

    private fun showResult() {
        try {
            val expression = getInputExpression()
            val result = Expression(expression).calculate()
            if (result.isNaN()) {
                // Show Error Message
                output.text = ""
                output.setTextColor(ContextCompat.getColor(this, R.color.red))
            } else {
                // Show Result
                output.text = DecimalFormat("0.######").format(result).toString()
                output.setTextColor(ContextCompat.getColor(this, R.color.green))
            }
        } catch (e: Exception) {
            // Show Error Message
            output.text = ""
            output.setTextColor(ContextCompat.getColor(this, R.color.red))
        }
    }
}