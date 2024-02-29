package com.example.demo
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val tipoDeCambioAEuros =0.92 // cambio de USD a EUR
    private val tipoDeCambioAMNX = 17.061981 // cambio de USD a MNX (Pesos Mexicanos)
    private val tipoDeCambioABolivares =
        36.036319 // cambio de USD a Bolívares Venezolanos
    private val tipoDeCambioALibras = 0.79 // cambio de USD a Libras Esterlinas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun calcular(view: View?) {
        val editText = findViewById<EditText>(R.id.editText)
        val cantidadString = editText.text.toString()

        val tipoMoneda = when {
            findViewById<RadioButton>(R.id.radioButton1).isChecked -> "Euros"
            findViewById<RadioButton>(R.id.radioButton).isChecked -> "MNX"
            findViewById<RadioButton>(R.id.radioButton3).isChecked -> "Bolívares"
            findViewById<RadioButton>(R.id.radioButton2).isChecked -> "Libras"
            else -> {
                Toast.makeText(this, "Por favor selecciona una moneda", Toast.LENGTH_SHORT).show()
                return
            }
        }

        try {
            val cantidad = cantidadString.toDouble()
            val conversion = when (tipoMoneda) {
                "Euros" -> cantidad * tipoDeCambioAEuros
                "MNX" -> cantidad * tipoDeCambioAMNX
                "Bolívares" -> cantidad * tipoDeCambioABolivares
                "Libras" -> cantidad * tipoDeCambioALibras
                else -> 0.0
            }
            val conversionString = String.format("%.2f", conversion)
            val mensaje = "$cantidadString USD son $conversionString $tipoMoneda"
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Por favor ingresa una cantidad válida.", Toast.LENGTH_SHORT)
                .show()
        }
    }
    // funcion para desmarcar los radiobuton al momento de seleccionar otro
    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton && view.isChecked) {
            val otherRadioButton = when (view.id) {
                R.id.radioButton1 -> findViewById<RadioButton>(R.id.radioButton)
                R.id.radioButton -> findViewById<RadioButton>(R.id.radioButton1)
                R.id.radioButton2 -> findViewById<RadioButton>(R.id.radioButton3)
                R.id.radioButton3 -> findViewById<RadioButton>(R.id.radioButton2)
                else -> null
            }
            otherRadioButton?.isChecked = false
        }
    }
}






