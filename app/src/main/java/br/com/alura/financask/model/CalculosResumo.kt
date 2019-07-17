package br.com.alura.financask.model

import java.math.BigDecimal

class CalculosResumo (private val transacoes: List<TransacaoItem>){

    private var totalReceita = BigDecimal(0.0)
    private var totalDespesa = BigDecimal(0.0)
    private var totalResumo = BigDecimal(0.0)

    fun receita() : BigDecimal{
        //Percorrendo a lista de transações para somar os valores
        for (transacao in transacoes) {
            if (transacao.tipo == Tipo.RECEITA) {
                totalReceita = totalReceita.plus(transacao.valor) //Somando valor da receita
            }
        }
        return totalReceita
    }

    fun despesa() : BigDecimal{

        for (transacao in transacoes) {
            if (transacao.tipo == Tipo.DESPESA) {
                totalDespesa = totalDespesa.plus(transacao.valor)
            }
        }
        return totalDespesa
    }

    fun total(): BigDecimal{
        return totalReceita.subtract(totalDespesa)
    }
}