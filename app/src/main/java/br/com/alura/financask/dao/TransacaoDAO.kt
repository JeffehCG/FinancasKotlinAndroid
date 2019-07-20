package br.com.alura.financask.dao

import br.com.alura.financask.model.TransacaoItem

//Classe para manter os dados salvos
class TransacaoDAO {

    //Lista de transações para ser acessada fora da classe
    val transacoes : List<TransacaoItem> = Companion.transacoes

    //O codigo colocado dentro de companion object é considerado como static (Pertence a classe, e não ao objeto estanciando) assim permanecendo em memoria
    companion object {
        //Lista de transações static da classe
        private val transacoes: MutableList<TransacaoItem> = mutableListOf() //listOf - metodo para gerar uma lista (Array) (val = constante - não muda de valor ; var = mutavel)
    }

    fun adicionaTransacao(transacaoItem: TransacaoItem){
        Companion.transacoes.add(transacaoItem)
    }

    fun alteraTransacao(transacaoItem: TransacaoItem, posicao: Int){
        Companion.transacoes[posicao] = transacaoItem
    }

    fun removeTransacao(posicao: Int){
        Companion.transacoes.removeAt(posicao)
    }
}