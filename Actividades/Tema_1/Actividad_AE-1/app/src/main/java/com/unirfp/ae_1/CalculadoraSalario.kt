package com.unirfp.ae_1

class CalculadoraSalario(private val contribuyente: Contribuyente) {
    var deduccionesTotales: Double = 0.0

    //se genera funcion calculo irpf
    fun calcularRetencionIRPF(salarioAnual: Double): Double {
        return when {
            salarioAnual <= 12450 -> salarioAnual * 0.19
            salarioAnual <= 20200 -> 12450 * 0.19 + (salarioAnual - 12450) * 0.24
            salarioAnual <= 35200 -> 12450 * 0.19 + (20200 - 12450) * 0.24 + (salarioAnual - 20200) * 0.30
            salarioAnual <= 60000 -> 12450 * 0.19 + (20200 - 12450) * 0.24 + (35200 - 20200) * 0.30 + (salarioAnual - 35200) * 0.37
            salarioAnual <= 300000 -> 12450 * 0.19 + (20200 - 12450) * 0.24 + (35200 - 20200) * 0.30 + (60000 - 35200) * 0.37 + (salarioAnual - 60000) * 0.45
            else -> 12450 * 0.19 + (20200 - 12450) * 0.24 + (35200 - 20200) * 0.30 + (60000 - 35200) * 0.37 + (300000 - 60000) * 0.45 + (salarioAnual - 300000) * 0.47
        }
    }

    // se genera funcion salario neto
    fun calcularSalarioNeto(): Double {
        //restricciones hijos
        val deduccionPorHijo = when (contribuyente.numeroHijos) {
            1 -> 1200.0
            2 -> 1500.0
            else -> 2000.0 * (contribuyente.numeroHijos - 2) + 1500.0
        }
        // restricciones invalidez
        val deduccionInvalidez = when {
            contribuyente.gradoInvalidez >= 65 -> 3000.0
            contribuyente.gradoInvalidez >= 33 -> 500.0
            else -> 0.0
        }
        //restriccion estado civil
        val deduccionEstadoCivil = when (contribuyente.estadoCivil) {
            "casado" -> if (contribuyente.numeroHijos > 0) 2000.0 else 1000.0
            "divorciado" -> if (contribuyente.numeroHijos > 0) 1500.0 else 500.0
            "viudo" -> 1000.0
            else -> 0.0
        }
        //restriccion grupo profesional
        val deduccionGrupoProfesional = when (contribuyente.grupoProfesional) {
            "A1" -> 2000.0
            "B1" -> 1000.0
            else -> 0.0
        }
        deduccionesTotales =
            deduccionPorHijo + deduccionInvalidez + deduccionEstadoCivil + deduccionGrupoProfesional
        // se generea calculo salarion bruto
        val salarioBrutoAnual = contribuyente.salarioBruto - deduccionesTotales
        //se genera calculo retencion irpf
        val retencionIRPFAnual = calcularRetencionIRPF(salarioBrutoAnual)
        return salarioBrutoAnual - retencionIRPFAnual
    }

    // se genera funcion salario neto mensual
    fun calcularSalarioNetoMensual(): Double {
        val salarioNetoAnual = calcularSalarioNeto()
        return salarioNetoAnual / contribuyente.numeroPagas
    }

    //se genera funcion mostrar
    fun mostrarResultados(): Map<String, Double> {
        val salarioNetoAnual = calcularSalarioNeto()
        val salarioNetoMensual = calcularSalarioNetoMensual()
        return mapOf(
            "Salario Bruto Anual" to contribuyente.salarioBruto,
            "Retención de IRPF Anual" to calcularRetencionIRPF(contribuyente.salarioBruto),
            "Deducciones Totales" to deduccionesTotales,
            "Salario Neto Anual" to salarioNetoAnual,
            "Salario Neto Mensual" to salarioNetoMensual
        )
    }
}
