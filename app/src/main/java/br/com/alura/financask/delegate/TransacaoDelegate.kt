package br.com.alura.financask.delegate

import br.com.alura.financask.model.TransacaoItem

//O Delegate tem a função de uma classe conseguir se comunicar com as funções de uma activity
interface TransacaoDelegate {

    fun delegate(transacao: TransacaoItem)
}