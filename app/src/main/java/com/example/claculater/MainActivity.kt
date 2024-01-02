package com.example.claculater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity() {
    private lateinit var txt_display_clicked_btn:TextView
    private lateinit var txt_result:TextView
    private lateinit var btn_backspace:ImageView
    private lateinit var card_percentage:CardView
    private lateinit var card_division:CardView
    private lateinit var card_btn_7:CardView
    private lateinit var card_btn_8:CardView
    private lateinit var card_btn_9:CardView
    private lateinit var card_btn_multiply:CardView

    private lateinit var card_btn_4:CardView
    private lateinit var card_btn_5:CardView
    private lateinit var card_btn_6:CardView
    private lateinit var card_btn_minus:CardView
    private lateinit var card_btn_1:CardView
    private lateinit var card_btn_2:CardView
    private lateinit var card_btn_3:CardView
    private lateinit var card_btn_plus:CardView
    private lateinit var card_btn_0:CardView
    private lateinit var card_btn_equal:CardView

    var firstNumber = ""
    var secondNumber=""
    var currentNumber = ""
    var currentOperator = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txt_display_clicked_btn = findViewById(R.id.txt_display_clicked_btn)
        txt_result = findViewById(R.id.txt_result)
        btn_backspace = findViewById(R.id.btn_backspace)
        card_percentage = findViewById(R.id.card_percentage)
        card_division = findViewById(R.id.card_division)
        card_btn_7 = findViewById(R.id.card_btn_7)
        card_btn_8 = findViewById(R.id.card_btn_8)
        card_btn_multiply = findViewById(R.id.card_btn_multiply)
        card_btn_4 = findViewById(R.id.card_btn_4)
        card_btn_5 = findViewById(R.id.card_btn_5)
        card_btn_5 = findViewById(R.id.card_btn_5)
        card_btn_6 = findViewById(R.id.card_btn_6)
        card_btn_minus = findViewById(R.id.card_btn_minus)
        card_btn_1 = findViewById(R.id.card_btn_1)
        card_btn_2 = findViewById(R.id.card_btn_2)
        card_btn_3 = findViewById(R.id.card_btn_3)
        card_btn_plus = findViewById(R.id.card_btn_plus)
        card_btn_0 = findViewById(R.id.card_btn_0)
        card_btn_equal = findViewById(R.id.card_btn_equal)

        initializeButtons()
      btn_backspace.setOnClickListener {
         if(currentNumber.isNotEmpty()){
             currentNumber = currentNumber.substring(0,currentNumber.length - 1)
             updateDisplay()
         }
      }


    }

    private fun initializeButtons() {
      var buttonValues = arrayOf(
          "7","8","9","/",
          "4","5","6","*",
          "1","2","3","-",
          "0","C","=","+"
      )
        var buttonIds = arrayOf(
            R.id.card_btn_7,R.id.card_btn_8,R.id.card_btn_9,R.id.card_division,
            R.id.card_btn_4,R.id.card_btn_5,R.id.card_btn_6,R.id.card_btn_multiply,
            R.id.card_btn_1,R.id.card_btn_2,R.id.card_btn_3,R.id.card_btn_minus,
            R.id.card_btn_0,R.id.card_Clear,R.id.card_btn_equal,R.id.card_btn_plus
        )
        buttonIds.forEachIndexed {index , buttonIds ->
            findViewById<CardView>(buttonIds).setOnClickListener {
                handleButtonClick(buttonValues[index])
            }
        }



    }



    private fun handleButtonClick(value:String){
       when(value){
           in "0".."9" -> appendDigit(value)
           "+" -> {
               appendOperator("+")
               currentOperator = "+"
           }
           "-" -> {
               appendOperator("-")
               currentOperator = "-"
           }
           "*" -> {
               appendOperator("*")
               currentOperator = "*"
           }
           "/" -> {
               appendOperator("/")
               currentOperator = "/"
           }
           "%" -> {
               appendOperator("%")
               currentOperator = "%"
           }
           "C" -> clearInput()
           "=" -> checkResult()
       }
   }
    private fun clearInput(){
        firstNumber = ""
        secondNumber = ""
        currentNumber = ""

        txt_display_clicked_btn.text = ""
        txt_result.text = ""
        updateDisplay()
    }
    private fun checkResult()
    {
        if(firstNumber.isNotEmpty() && currentNumber.isNotEmpty())
        {
            secondNumber = currentNumber
            var result = peformCalculation(currentOperator)
            txt_result.text = result.toString()
            firstNumber = ""
            secondNumber = ""
            currentNumber = ""
        }
    }

    private fun peformCalculation(operator: String): Double {
        return when(operator)
        {
            "+" -> firstNumber.toDouble() + secondNumber.toDouble()
            "-" -> firstNumber.toDouble() - secondNumber.toDouble()
            "*" -> firstNumber.toDouble() * secondNumber.toDouble()
            "/" -> firstNumber.toDouble() / secondNumber.toDouble()
            "%" -> firstNumber.toDouble() % secondNumber.toDouble()

            else -> {throw IllegalArgumentException("Invalid !!!! Operator")}
        }


    }


    private fun appendDigit(digit: String)
    {
        currentNumber += digit
                updateDisplay()
    }

    private fun appendOperator(operator :String)
    {
        if(currentNumber.isNotEmpty())
        {
            firstNumber = currentNumber
            currentNumber = ""
        }
        updateDisplay()
    }
    private fun updateDisplay(){
        txt_display_clicked_btn.text = currentNumber
    }
}