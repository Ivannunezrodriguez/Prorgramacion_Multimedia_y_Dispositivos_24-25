package com.unirfp.ae_1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultadoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        // Obtener los datos enviados desde MainActivity
        val salarioNeto = intent.getDoubleExtra("SALARIO_NETO", 0.0)
        val deduccionesTotales = intent.getDoubleExtra("DEDUCCIONES_TOTALES", 0.0)
        val irpf = intent.getDoubleExtra("IRPF", 0.0)

        // Obtener referencias a los TextViews en el diseño
        val tvSalarioNeto = findViewById<TextView>(R.id.tvSalarioNeto)
        val tvDeduccionesTotales = findViewById<TextView>(R.id.tvDeduccionesTotales)
        val tvIrpf = findViewById<TextView>(R.id.tvIrpf)

        // Mostrar los resultados en los TextViews
        tvSalarioNeto.text = "Salario Neto Anual:\n $salarioNeto"
        tvDeduccionesTotales.text = "Deducciones Totales:\n $deduccionesTotales"
        tvIrpf.text = "Retención de IRPF:\n $irpf"
    }
}
