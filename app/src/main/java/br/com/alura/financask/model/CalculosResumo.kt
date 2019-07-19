package br.com.alura.financask.model

import java.math.BigDecimal

class CalculosResumo(private val transacoes: List<TransacaoItem>) {

    //Percorrendo a lista de transações para somar os valores
    val receita: BigDecimal get() = calcularTipo(Tipo.RECEITA)

    val despesa: BigDecimal get() = calcularTipo(Tipo.DESPESA)

    //Funções de uma linha podem ser declaradas dessa forma
    fun total(): BigDecimal = receita.subtract(despesa)

    //Metodo para efetuar o calculo pelos tipos
    private fun calcularTipo(tipo: Tipo): BigDecimal {
//        for (transacao in transacoes) {
//            if (transacao.tipo == Tipo.RECEITA) {
//                totalReceita = totalReceita.plus(transacao.valor) //Somando valor da receita
//            }
//        }
//        return totalReceita

        //Percorrendo o valores de outra forma ( e convertendo e BigDecimal)

        val somandoAsTransacoesPorTipo = transacoes
                .filter({ it.tipo == tipo }) //Pode ser it, ou especificando a transacao em si
                .sumByDouble({ transacao -> transacao.valor.toDouble() })
        return BigDecimal(somandoAsTransacoesPorTipo)
    }
}