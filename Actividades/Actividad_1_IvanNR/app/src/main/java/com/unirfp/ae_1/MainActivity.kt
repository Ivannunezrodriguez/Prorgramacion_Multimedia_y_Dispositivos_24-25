package com.unirfp.ae_1

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Variables de la vista
        val etSalarioBruto = findViewById<EditText>(R.id.etSalarioBruto)
        val spNumeroPagas = findViewById<Spinner>(R.id.spNumeroPagas)
        val etEdad = findViewById<EditText>(R.id.etEdad)
        val spGrupoProfesional = findViewById<Spinner>(R.id.spGrupoProfesional)
        val spGradoDiscapacidad = findViewById<Spinner>(R.id.spGradoDiscapacidad)
        val spEstadoCivil = findViewById<Spinner>(R.id.spEstadoCivil)
        val etNumeroHijos = findViewById<EditText>(R.id.etNumeroHijos)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)

        // Configuración de los Spinners
        configurarSpinner(spNumeroPagas, R.array.numero_pagas)
        configurarSpinner(spGrupoProfesional, R.array.grupos_profesionales)
        configurarSpinner(spGradoDiscapacidad, R.array.grados_discapacidad)
        configurarSpinner(spEstadoCivil, R.array.estados_civiles)

        // Funcionalidad del botón
        btnCalcular.setOnClickListener {
            // Obtener los valores de los campos de entrada
            val salarioBruto = etSalarioBruto.text.toString().toDoubleOrNull() ?: 0.0
            val numeroPagas = spNumeroPagas.selectedItem.toString().toInt()
            val edad = etEdad.text.toString().toIntOrNull() ?: 0
            val grupoProfesional = spGrupoProfesional.selectedItem.toString()
            val gradoDiscapacidad = spGradoDiscapacidad.selectedItem.toString()
            val estadoCivil = spEstadoCivil.selectedItem.toString()
            val numeroHijos = etNumeroHijos.text.toString().toIntOrNull() ?: 0

            // Crear el objeto Contribuyente
            val contribuyente = Contribuyente(
                salarioBruto = salarioBruto,
                numeroPagas = numeroPagas,
                edad = edad,
                grupoProfesional = grupoProfesional,
                gradoInvalidez = gradoDiscapacidad.replace("%", "").toDoubleOrNull() ?: 0.0,
                estadoCivil = estadoCivil,
                numeroHijos = numeroHijos
            )

            // Calcular el salario neto, deducciones y retención de IRPF
            val calculadora = CalculadoraSalario(contribuyente)
            val salarioNeto = calculadora.calcularSalarioNeto()
            val deduccionesTotales = calculadora.deduccionesTotales
            val irpf = calculadora.calcularRetencionIRPF(contribuyente.salarioBruto)

            // Ir a ResultadoActivity y enviar los datos de los cálculos
            val intent = Intent(this, ResultadoActivity::class.java).apply {
                putExtra("SALARIO_NETO", salarioNeto)
                putExtra("DEDUCCIONES_TOTALES", deduccionesTotales)
                putExtra("IRPF", irpf)
            }
            startActivity(intent)
        }
    }

    // se le da usabilidad a todos los spinner
    private fun configurarSpinner(spinner: Spinner, arrayResource: Int) {
        val adapter = ArrayAdapter.createFromResource(
            this,
            arrayResource,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.setSelection(0)
    }
}
