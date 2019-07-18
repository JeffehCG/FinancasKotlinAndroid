package br.com.alura.financask.extesion

import java.text.SimpleDateFormat
import java.util.*

fun String.limitaEmAte(caracteres: Int) : String{
    //Formatando nome da categoria, caso seja muito grande
    if(this.length > caracteres){
        return "${this.substring(0, caracteres)}..." //pegando do primeiro caracter até o desejado(caracteres)
    }
    return this
}

fun String.converteParaCalendar(): Calendar {
    val formatoBrasil = SimpleDateFormat("dd/MM/YYYY")
    val dataConvertida = formatoBrasil.parse(this)
    val data = Calendar.getInstance()
    data.time = dataConvertida
    return data
}
