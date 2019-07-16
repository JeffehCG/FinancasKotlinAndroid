package br.com.alura.financask.model

import java.math.BigDecimal
import java.util.*

//Classe de item da transação
class TransacaoItem (val valor: BigDecimal, //Atributos da classe

                     val categoria: String,
                     val data: Calendar){ //No constructor é passado o valor, categoria e data
}