package com.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge

class MainActivity : AppCompatActivity() {
    private lateinit var clr: TextView
    private lateinit var ip: TextView
    private lateinit var oup: TextView
    private lateinit var bl: TextView
    private lateinit var br: TextView
    private lateinit var one: TextView
    private lateinit var two: TextView
    private lateinit var three: TextView
    private lateinit var four: TextView
    private lateinit var five: TextView
    private lateinit var six: TextView
    private lateinit var seven: TextView
    private lateinit var eight: TextView
    private lateinit var nine: TextView
    private lateinit var zero: TextView
    private lateinit var dot: TextView
    private lateinit var divi: TextView
    private lateinit var prod: TextView
    private lateinit var sub: TextView
    private lateinit var add: TextView
    private lateinit var equal: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        clr = findViewById<TextView>(R.id.clear)
        ip = findViewById(R.id.input)
        oup = findViewById(R.id.output)
        bl = findViewById(R.id.leftBracket)
        br = findViewById(R.id.rightBracket)
        one = findViewById(R.id.one)
        two = findViewById(R.id.two)
        three = findViewById(R.id.three)
        four = findViewById(R.id.four)
        five = findViewById(R.id.five)
        six = findViewById(R.id.six)
        seven = findViewById(R.id.seven)
        eight = findViewById(R.id.eight)
        nine = findViewById(R.id.nine)
        zero = findViewById(R.id.zero)
        dot = findViewById(R.id.dot)
        divi = findViewById(R.id.divide)
        prod = findViewById(R.id.multiply)
        sub = findViewById(R.id.sub)
        add = findViewById(R.id.add)
        equal = findViewById(R.id.equal)


        clr.setOnClickListener {
            ip.text = ""
            oup.text = ""
        }
        bl.setOnClickListener {
            ip.text = addToInputText("(")
        }

        br.setOnClickListener {
            ip.text = addToInputText(")")
        }

        zero.setOnClickListener {
            ip.text = addToInputText("0")
        }
        one.setOnClickListener {
            ip.text = addToInputText("1")
        }
        two.setOnClickListener {
            ip.text = addToInputText("2")
        }
        three.setOnClickListener {
            ip.text = addToInputText("3")
        }
        four.setOnClickListener {
            ip.text = addToInputText("4")
        }
        five.setOnClickListener {
            ip.text = addToInputText("5")
        }
        six.setOnClickListener {
            ip.text = addToInputText("6")
        }
        seven.setOnClickListener {
            ip.text = addToInputText("7")
        }
        eight.setOnClickListener {
            ip.text = addToInputText("8")
        }
        nine.setOnClickListener {
            ip.text = addToInputText("9")
        }
        dot.setOnClickListener {
            ip.text = addToInputText(".")
        }
        divi.setOnClickListener {
            ip.text = addToInputText("/")
        }
        prod.setOnClickListener {
            ip.text = addToInputText("*")
        }
        sub.setOnClickListener {
            ip.text = addToInputText("-")
        }
        add.setOnClickListener {
            ip.text = addToInputText("+")
        }
        equal.setOnClickListener {
            showResult()
        }
    }
    private fun addToInputText(buttonValue:String): String {
        return "${ip.text}$buttonValue"

    }
    private fun getInputExpression(): String{
        val expr = ip.text.replace(Regex("x"), "*")
        return expr
    }
    @SuppressLint("SetTextI18n")
    private fun showResult(){
        try{
            val e = getInputExpression()
            val result = evaluate(e)
            if(result.isNaN()){
                oup.text = "Error"
            }else{
                oup.text = result.toString()

            }
        } catch(e: Exception){
            oup.text = e.message ?: "Unknown Error"
        }
    }
    private fun evaluate(expression: String): Double {
        val parts = expression.split(Regex("(?<=[-+*/()])|(?=[-+*/()])")).filter { it.isNotBlank() }
        val values = mutableListOf<Double>()
        val operators = mutableListOf<Char>()

        for (part in parts) {
            if (part.isNumber()) {
                values.add(part.toDouble())
            } else if (part.isOperator()) {
                while (operators.isNotEmpty() && precedence(operators.last()) >= precedence(part[0])) {
                    val op = operators.removeLast()
                    val operand2 = values.removeLast()
                    val operand1 = values.removeLast()
                    values.add(applyOperation(operand1, operand2, op))
                }
                operators.add(part[0])
            } else if (part == "(") {
                operators.add(part[0])
            } else if (part == ")") {
                while (operators.isNotEmpty() && operators.last() != '(') {
                    val op = operators.removeLast()
                    val operand2 = values.removeLast()
                    val operand1 = values.removeLast()
                    values.add(applyOperation(operand1, operand2, op))
                }
                operators.removeLast()
            }
        }

        while (operators.isNotEmpty()) {
            val op = operators.removeLast()
            val operand2 = values.removeLast()
            val operand1 = values.removeLast()
            values.add(applyOperation(operand1, operand2, op))
        }

        return values.first()
    }

    private fun String.isNumber(): Boolean = matches(Regex("-?\\d+(\\.\\d+)?"))
    private fun String.isOperator(): Boolean = length == 1 && this[0] in setOf('+', '-', '*', '/')
    private fun precedence(operator: Char): Int = when (operator) {
        '+', '-' -> 1
        '*', '/' -> 2
        else -> 0
    }
    private fun applyOperation(operand1: Double, operand2: Double, operator: Char): Double = when (operator) {
        '+' -> operand1 + operand2
        '-' -> operand1 - operand2
        '*' -> operand1 * operand2
        '/' -> operand1 / operand2
        else -> throw IllegalArgumentException("Invalid operator")
    }
}

open class AppCompatActivity {

}
