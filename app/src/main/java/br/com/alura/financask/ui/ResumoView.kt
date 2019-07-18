package br.com.alura.financask.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import br.com.alura.financask.R
import br.com.alura.financask.extesion.formatoMoedaBrasil
import br.com.alura.financask.model.CalculosResumo
import br.com.alura.financask.model.Tipo
import br.com.alura.financask.model.TransacaoItem
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

//Calculando os valores da view
class ResumoView(private val context: Context,
                 private val view: View,
                 private val transacoes: List<TransacaoItem>) { //Essa classe recebe uma view como parametro

    //Instanciando classe para calcular os valores do resumo
    private val resumo: CalculosResumo = CalculosResumo(transacoes)

    private val corReceita = ContextCompat.getColor(context, R.color.receita)
    private val corDespesa = ContextCompat.getColor(context, R.color.despesa)

    //Função que inicializara todas as outras funções, para atualizar valores do resumo
    fun atualizaResumo(){
        adicionaTotalReceitaAoResumo()
        adicionaTotalDespesaAoResumo()
        adicionaTotalDoResumo()
    }

    private fun adicionaTotalReceitaAoResumo() {
        val totalReceita = resumo.receita
        //Atribuindo valor a view
        with(view.resumo_card_receita){
            setTextColor(corReceita) //Cor
            text = totalReceita.formatoMoedaBrasil() //Valor
        }
    }

    private fun adicionaTotalDespesaAoResumo() {
        val totalDespesa = resumo.despesa
        //Atribuindo valor a view
        with(view.resumo_card_despesa){
            setTextColor(corDespesa) //Cor
            text = totalDespesa.formatoMoedaBrasil() //Valor
        }
    }

    private fun adicionaTotalDoResumo(){

        val totalResumo = resumo.total()
        //Retornando a cor de acordo com o valor total
        val cor = definindoCorTotal(totalResumo)

        //Atribuindo valor a view
        with(view.resumo_card_total){
            setTextColor(cor) //Cor
            text = totalResumo.formatoMoedaBrasil()
        }
    }

    private fun definindoCorTotal(totalResumo: BigDecimal): Int {
        return if (totalResumo >= BigDecimal.ZERO) { //CompareTo retorna -1 para valores menores, 0 para igual e 1 para maiores
            corReceita
        } else {
            corDespesa
        }
    }
}