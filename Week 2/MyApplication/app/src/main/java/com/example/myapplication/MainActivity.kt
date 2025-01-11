package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Global variables for UI elements
lateinit var btnOk: Button
lateinit var btnCancel: Button
lateinit var txtName: EditText

lateinit var edtNumber1: EditText
lateinit var edtNumber2: EditText
lateinit var txtResult: TextView

lateinit var btnAdd: Button
lateinit var btnSubtract: Button
lateinit var btnMultiply: Button
lateinit var btnDivide: Button


class Cars(var brandi:String, var modeli: String, var yeari:Int){
    fun getBrand()= brandi
    fun getModel()= modeli
    fun getYear() = yeari
}
class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        fun test(a:Int, b:Int) = a + b;

        fun test1(a:Int, b:Int) :Int{
            return a+b
        }


        btnOk = findViewById(R.id.btnOk)
        btnCancel = findViewById(R.id.btnCancel)
        txtName = findViewById(R.id.txtName)

        edtNumber1 = findViewById(R.id.edtNumber1)
        edtNumber2 = findViewById(R.id.edtNumber2)
        txtResult = findViewById(R.id.txtResult)

        btnAdd = findViewById(R.id.btnAdd)
        btnSubtract = findViewById(R.id.btnSubtract)
        btnMultiply = findViewById(R.id.btnMultiply)
        btnDivide = findViewById(R.id.btnDivide)


        btnOk.setOnClickListener(this)
        btnCancel.setOnClickListener(this)
        btnAdd.setOnClickListener { calculate(it) }
        btnSubtract.setOnClickListener { calculate(it) }
        btnMultiply.setOnClickListener { calculate(it) }
        btnDivide.setOnClickListener { calculate(it) }
    }

    override fun onClick(v: View?) {
        val text = txtName.text

        when (v?.id) {
            R.id.btnOk -> {
                val car = Cars("Ford", "Mustang", 1979)
                txtName.setText(car.getModel())
               // txtName.setText("Hello $text")
            }
            R.id.btnCancel -> {
                txtName.setText("Cancel")
            }

            //R.id.btnOk->{
            //    var text = txtName.text
              //  when(v?.id){
                //    R.id.btnOk->{
                 //       txtName
                   // }
                }
                //val car = arrayOf("MT","CBR","Kawasaki")
               // car[1] = "Kia"
                //txtName.setText(car.joinToString())

               // var s=""
               // hehe@for (i in car.indices) {
                //    if(car.get(i)=="Kia")break@hehe
               //     s += car.get(i)
              //  }
              ///  txtName.setText(s)
            }


        }


    private fun calculate(v: View?) {
        val input1 = edtNumber1.text.toString()
        val input2 = edtNumber2.text.toString()

        try {
            val number1 = input1.toDouble()
            val number2 = input2.toDouble()

            val result = when (v?.id) {
                R.id.btnAdd -> number1 + number2
                R.id.btnSubtract -> number1 - number2
                R.id.btnMultiply -> number1 * number2
                R.id.btnDivide -> {
                    if (number2 != 0.0) number1 / number2 else throw ArithmeticException("Division by zero")
                }
                else -> throw IllegalArgumentException("Unknown operation")
            }

            txtResult.text = "Result: $result"
        } catch (e: NumberFormatException) {
            txtResult.text = "Invalid input. Please enter numbers."
        } catch (e: ArithmeticException) {
            txtResult.text = "Error: ${e.message}"
        } catch (e: Exception) {
            txtResult.text = "Unexpected error: ${e.message}"
        }
    }

