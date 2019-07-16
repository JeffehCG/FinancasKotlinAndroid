package br.com.alura.financask.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.alura.financask.R
import br.com.alura.financask.model.TransacaoItem
import kotlinx.android.synthetic.main.transacao_item.view.*
import java.text.SimpleDateFormat

// BaseAdapter -- Transforma uma classe em um adapter - nesse caso tranformar os itens de uma lista
//alt + enter = implements members = criar as funções basicas de um adapter
class ListaTransacoesAdapter(transacoes: List<TransacaoItem>,
                             context: Context) : BaseAdapter(){

    //Transformando o contructor passado em atributos
    private val transacoes = transacoes
    private val context = context

    //Função que ira criar a view (Retorna as view para cada item da lista)
    //É preciso passar a posição do item, a view em ci, e a viewPai
    override fun getView(posicao: Int, view: View?, parent: ViewGroup?): View {

        //LayoutInflater - criar telas de acordo com uma view passada (é preciso passar o contexto da activity que esta chamando o metodo "this dela")
        val viewItemTransacao = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)

        val transacaoItem = transacoes[posicao] //Recebendo o item da transação

        //Atribuindo valores aos atribuotos da view, de acordo com o item passado (Equivalente ao setText)
        viewItemTransacao.transacao_valor.text = transacaoItem.valor.toString()
        viewItemTransacao.transacao_categoria.text = transacaoItem.categoria

        //Formatando a hora, e atribuindo valor a view
        val formatoDataBrasil = "dd/MM/YYYY"
        val format = SimpleDateFormat(formatoDataBrasil)
        val dataFormatada = format.format(transacaoItem.data.time)
        viewItemTransacao.transacao_data.text = dataFormatada

        return viewItemTransacao
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