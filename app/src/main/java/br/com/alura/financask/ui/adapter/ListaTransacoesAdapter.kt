package br.com.alura.financask.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.alura.financask.R
import br.com.alura.financask.extesion.formataParaDataBrasil
import br.com.alura.financask.extesion.formatoMoedaBrasil
import br.com.alura.financask.extesion.limitaEmAte
import br.com.alura.financask.model.Tipo
import br.com.alura.financask.model.TransacaoItem
import kotlinx.android.synthetic.main.transacao_item.view.*

// BaseAdapter -- Transforma uma classe em um adapter - nesse caso tranformar os itens de uma lista
//alt + enter = implements members = criar as funções basicas de um adapter
class ListaTransacoesAdapter(private val transacoes: List<TransacaoItem>,
                             private val context: Context) : BaseAdapter() {

    private val limiteDaCategoria = 14

    //Função que ira criar a view (Retorna as view para cada item da lista)
    //É preciso passar a posição do item, a view em ci, e a viewPai
    override fun getView(posicao: Int, view: View?, parent: ViewGroup?): View {

        //LayoutInflater - criar telas de acordo com uma view passada (é preciso passar o contexto da activity que esta chamando o metodo "this dela")
        val viewItemTransacao = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)

        val transacaoItem = transacoes[posicao] //Recebendo o item da transação

        //Atribuindo valores aos atribuotos da view, de acordo com o item passado (Equivalente ao setText)
        adicionaValorEIcone(transacaoItem, viewItemTransacao)
        adicionaCategoria(viewItemTransacao, transacaoItem)
        adicionaData(viewItemTransacao, transacaoItem) //Função criada dentro da pasta extension

        return viewItemTransacao
    }

    private fun adicionaData(viewItemTransacao: View, transacaoItem: TransacaoItem) {
        viewItemTransacao.transacao_data.text = transacaoItem.data.formataParaDataBrasil()
    }

    private fun adicionaCategoria(viewItemTransacao: View, transacaoItem: TransacaoItem) {
        viewItemTransacao.transacao_categoria.text = transacaoItem.categoria.limitaEmAte(limiteDaCategoria)
    }

    private fun adicionaValorEIcone(transacaoItem: TransacaoItem, viewItemTransacao: View) {
        //Identificando se a transação é uma despesa ou receita
        var cor = 0
        var icone = 0
        if (transacaoItem.tipo == Tipo.RECEITA) {
            cor = ContextCompat.getColor(context, R.color.receita)//Alteranco a cor do valor
            icone = R.drawable.icone_transacao_item_receita//Adicionando icone
        } else {
            cor = ContextCompat.getColor(context, R.color.despesa)//Alteranco a cor do valor
            icone = R.drawable.icone_transacao_item_despesa//Adicionando icone
        }
        viewItemTransacao.transacao_valor.setTextColor(cor)
        viewItemTransacao.transacao_icone.setBackgroundResource(icone)
        viewItemTransacao.transacao_valor.text = transacaoItem.valor.formatoMoedaBrasil()
    }

    //Retornando o item da transação, de acordo com a posicao passada (exemplo - como array[0])
    override fun getItem(posicao: Int): TransacaoItem { //Retornando uma string (se colocar any retorna qualquer coisa)
        return transacoes[posicao]
    }

    //Retorna 0 por que não tem nenhum id na lista
    override fun getItemId(p0: Int): Long {
        return 0
    }

    //Retornando o tamanho da lista de transações
    override fun getCount(): Int {
        return transacoes.size
    }
}