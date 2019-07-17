package br.com.alura.financask.extesion

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

//Convertendo moeda para Real
fun BigDecimal.formatoMoedaBrasil() : String{
    val formatoBrasil = DecimalFormat.getCurrencyInstance(Locale("pt", "br"))
    return formatoBrasil.format(this).replace("R$", "R$ ").replace("-R$", "R$")
}
