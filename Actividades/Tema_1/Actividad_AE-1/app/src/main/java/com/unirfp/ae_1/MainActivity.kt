package com.unirfp.ae_1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.CalculadoraSalario
import com.Contribuyente

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun main() {
        // Ejemplo de uso
        val contribuyente = Contribuyente(
            salarioBruto = 30000.0,
            numeroPagas = 12,
            edad = 30,
            grupoProfesional = "B",
            gradoInvalidez = 40.0,
            estadoCivil = "casado",
            numeroHijos = 2
        )

        val calculadora = CalculadoraSalario(contribuyente)
        calculadora.mostrarResultados()
    }
}