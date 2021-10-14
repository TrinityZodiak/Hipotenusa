package com.example.hipotenusa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.DialogInterface

import android.R
import android.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.*
import android.widget.EditText
import java.text.DecimalFormat

class MainActivity : AppCompatActivity(){
    var calcular: Button? = null
    var limpiar: Button? = null
    var teC: EditText? = null
    var teB: EditText? = null
    var resultado: TextView? = null
    var formato = DecimalFormat("#.00")
    var cateto_c: String? = null
    var cateto_b: String? = null
    var respuesta: String? = null
    var dialog: AlertDialog.Builder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calcular = findViewById<View>(R.id.btnCalcular) as Button
        limpiar = findViewById<View>(R.id.btnLimpiar) as Button
        teC = findViewById<View>(R.id.edtxtC) as EditText
        teB = findViewById<View>(R.id.edtxtB) as EditText
        resultado = findViewById<View>(R.id.txtResultado) as TextView
        limpiar!!.isEnabled = false
        calcular!!.setOnClickListener {

            cateto_b = teB!!.text.toString()
            cateto_c = teC!!.text.toString()

            if (cateto_c!!.length == 0) { //Verificar si no hay nada escrito
                dialog = AlertDialog.Builder(this@MainActivity)
                dialog!!.setTitle("Error")
                dialog!!.setMessage("Ingresar el valor del cateto menor 'c'")
                dialog!!.setCancelable(false)
                dialog!!.setPositiveButton(
                    "Cerrar"
                ) { dialogo, id ->
                    dialogo.cancel()
                    teC!!.requestFocus()
                }
                dialog!!.show()
            } else {
                if (cateto_b!!.length == 0) { //Verificar si no hay nada escrito
                    dialog = AlertDialog.Builder(this@MainActivity)
                    dialog!!.setTitle("Error")
                    dialog!!.setMessage("Ingresar el valor del cateto mayor 'b'")
                    dialog!!.setCancelable(false)
                    dialog!!.setPositiveButton(
                        "Cerrar"
                    ) { dialogo, id ->
                        dialogo.cancel()
                        teB!!.requestFocus()
                    }
                    dialog!!.show()
                } else {
                    val c = cateto_c!!.toDouble()
                    val b = cateto_b!!.toDouble()
                    val r = Math.pow(
                        Math.pow(c, 2.0) + Math.pow(
                            b,
                            2.0
                        ), 0.5
                    )
                    respuesta = formato.format(r).toString()
                    if (r % 1.0 == 0.0) {
                        respuesta = r.toString()
                    }
                    resultado!!.text = "La hipotenusa 'a' es:  $respuesta"
                    limpiar!!.isEnabled = true
                    calcular!!.isEnabled = false
                }
            }
        }
        limpiar!!.setOnClickListener { limpiar_controles() }
    }

    fun limpiar_controles() {
        teB!!.setText("")
        teC!!.setText("")
        resultado!!.text = ""
        teC!!.requestFocus()
        limpiar!!.isEnabled = false
        calcular!!.isEnabled = true
    }
}