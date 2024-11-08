package com

class CalculadoraSalario(private val contribuyente: Contribuyente) {
    private var deduccionesTotales: Double = 0.0
    // Calcular la retención de IRPF según los tramos de 2023
    private fun calcularRetencionIRPF(salarioAnual: Double): Double {
        return when {
            salarioAnual <= 12450 -> salarioAnual * 0.19
            salarioAnual <= 20200 -> 12450 * 0.19 + (salarioAnual - 12450) * 0.24
            salarioAnual <= 35200 -> 12450 * 0.19 + (20200 - 12450) * 0.24 + (salarioAnual - 20200) * 0.30
            salarioAnual <= 60000 -> 12450 * 0.19 + (20200 - 12450) * 0.24 + (35200 - 20200) * 0.30 + (salarioAnual - 35200) * 0.37
            salarioAnual <= 300000 -> 12450 * 0.19 + (20200 - 12450) * 0.24 + (35200 - 20200) * 0.30 + (60000 - 35200) * 0.37 + (salarioAnual - 60000) * 0.45
            else -> 12450 * 0.19 + (20200 - 12450) * 0.24 + (35200 - 20200) * 0.30 + (60000 - 35200) * 0.37 + (300000 - 60000) * 0.45 + (salarioAnual - 300000) * 0.47
        }
    }

    // Calcular el salario neto anual
    fun calcularSalarioNeto(): Double {
        // Deducción por hijos (1.200 € por el primero, 1.500 € por el segundo, 2.000 € por cada hijo adicional)
        val deduccionPorHijo = when (contribuyente.numeroHijos) {
            1 -> 1200.0
            2 -> 1500.0
            else -> 2000.0 * (contribuyente.numeroHijos - 2) + 1500.0
        }

        // Deducción por invalidez (500 € para invalidez de 33% a 64%, 3000 € para 65% o más)
        val deduccionInvalidez = when {
            contribuyente.gradoInvalidez >= 65 -> 3000.0
            contribuyente.gradoInvalidez >= 33 -> 500.0
            else -> 0.0
        }
        //Calcular deducciones totales
      deduccionesTotales = deduccionPorHijo + deduccionInvalidez

        // Calcular salario bruto anual después de deducciones por hijos y invalidez
        val salarioBrutoAnual =
            contribuyente.salarioBruto - deduccionPorHijo - deduccionInvalidez

        // Cálculo de retención de IRPF en función del salario bruto anual
        val retencionIRPFAnual = calcularRetencionIRPF(salarioBrutoAnual)

        // Salario neto anual después de retención de IRPF
        val salarioNetoAnual = salarioBrutoAnual - retencionIRPFAnual

        return salarioNetoAnual

    }
    // Mostrar los resultados finales
    fun mostrarResultados() {
        val salarioNetoAnual = calcularSalarioNeto()
        println("Salario Bruto Anual: ${contribuyente.salarioBruto}")
        println("Retención de IRPF Anual: ${calcularRetencionIRPF(contribuyente.salarioBruto)}")
        println("Deducciones Simuladas por Hijos e Invalidez: $deduccionesTotales")
        println("Salario Neto Anual: $salarioNetoAnual")
    }}

