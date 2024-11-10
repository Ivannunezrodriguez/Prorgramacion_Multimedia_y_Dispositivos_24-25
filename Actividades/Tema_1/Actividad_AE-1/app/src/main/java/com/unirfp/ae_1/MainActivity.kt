package com.unirfp.ae_1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etSalarioBruto = findViewById<EditText>(R.id.etSalarioBruto)
        val etNumeroPagas = findViewById<EditText>(R.id.etNumeroPagas)
        val etEdad = findViewById<EditText>(R.id.etEdad)
        val etGrupoProfesional = findViewById<EditText>(R.id.etGrupoProfesional)
        val etGradoInvalidez = findViewById<EditText>(R.id.etGradoInvalidez)
        val etEstadoCivil = findViewById<EditText>(R.id.etEstadoCivil)
        val etNumeroHijos = findViewById<EditText>(R.id.etNumeroHijos)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)

        btnCalcular.setOnClickListener {
            // Obtener los valores de los campos de entrada
            val salarioBruto = etSalarioBruto.text.toString().toDoubleOrNull() ?: 0.0
            val numeroPagas = etNumeroPagas.text.toString().toIntOrNull() ?: 0
            val edad = etEdad.text.toString().toIntOrNull() ?: 0
            val grupoProfesional = etGrupoProfesional.text.toString()
            val gradoInvalidez = etGradoInvalidez.text.toString().toDoubleOrNull() ?: 0.0
            val estadoCivil = etEstadoCivil.text.toString()
            val numeroHijos = etNumeroHijos.text.toString().toIntOrNull() ?: 0

            // Crear el objeto Contribuyente
            val contribuyente = Contribuyente(
                salarioBruto = salarioBruto,
                numeroPagas = numeroPagas,
                edad = edad,
                grupoProfesional = grupoProfesional,
                gradoInvalidez = gradoInvalidez,
                estadoCivil = estadoCivil,
                numeroHijos = numeroHijos
            )

            // Calcular el salario neto, deducciones y retención de IRPF
            val calculadora = CalculadoraSalario(contribuyente)
            val salarioNeto = calculadora.calcularSalarioNeto()
            val deduccionesTotales = calculadora.deduccionesTotales
            val irpf = calculadora.calcularRetencionIRPF(contribuyente.salarioBruto)

            // Crear el Intent para iniciar ResultadoActivity y enviar los datos
            val intent = Intent(this, ResultadoActivity::class.java).apply {
                putExtra("SALARIO_NETO", salarioNeto)
                putExtra("DEDUCCIONES_TOTALES", deduccionesTotales)
                putExtra("IRPF", irpf)
            }
            startActivity(intent)
        }
    }
}
