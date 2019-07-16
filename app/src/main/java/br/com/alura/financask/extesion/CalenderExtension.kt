package br.com.alura.financask.extesion

import java.text.SimpleDateFormat
import java.util.*

//Criando uma função que é chamada apartir do proprio Calendar (Para ja trazer a data formatada)
fun Calendar.formataParaDataBrasil() : String{

    //Formatando a hora, e atribuindo valor a view
    val formatoDataBrasil = "dd/MM/YYYY"
    val format = SimpleDateFormat(formatoDataBrasil)
    return format.format(this.time)

}
