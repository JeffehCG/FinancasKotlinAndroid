package br.com.alura.financask.model

import java.math.BigDecimal
import java.util.*

//Classe de item da transação
class TransacaoItem (val valor: BigDecimal, //Atributos da classe
                     val categoria: String = "Indefinida",
                     val tipo: Tipo, //Tipo da receita, que podem ser sa da classe tipo
                     val data: Calendar = Calendar.getInstance()){ //Caso não seja passado o valor pelo construtor, o padrão sera Calendar.getInstance()(data Atual)

    //Construtor secundario
    //É preciso chamar o construtor primario (this)
    //constructor(valor: BigDecimal, tipo: Tipo) : this(valor, "Indefinida", tipo) //Passando valor padão para o atributo categoria
}