package br.com.alura.financask.ui.dialog

import android.content.Context
import android.view.ViewGroup
import br.com.alura.financask.R
import br.com.alura.financask.delegate.TransacaoDelegate
import br.com.alura.financask.extesion.formataParaDataBrasil
import br.com.alura.financask.model.Tipo
import br.com.alura.financask.model.TransacaoItem

class AlteraTransacaoDialog(viewGroup: ViewGroup,
                            private val context: Context): FormularioTransacaoDialog(viewGroup, context) {

    //Classes filhas devem obrigatoriamente chamar as classes abstratas (override) da classe pai
    override val tituloBotaoPositivo: String
        get() = "Alterar"

    override fun getTituloPeloTipo(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.string.altera_receita
        } else {
            return R.string.altera_despesa
        }
    }

    //Recebendo transação para alterala
    fun chamaDialog(transacaoItem: TransacaoItem, delegate:(transacao: TransacaoItem) -> Unit) {

        val tipo = transacaoItem.tipo

        super.chamaDialog(tipo, delegate) //super é usado quando metodos da classe pai não chamados

        //Atribuindo os valores da transação recebida
        inicializaCampos(transacaoItem)
    }

    private fun inicializaCampos(transacaoItem: TransacaoItem) {
        //Os campos abaixos podem ser usados por que estão definidos como protected na classe pai
        val tipo = transacaoItem.tipo
        inicializaCampoValor(transacaoItem)
        inicializaCampoData(transacaoItem)
        inicializaCampoCategoria(tipo, transacaoItem)
    }

    private fun inicializaCampoCategoria(tipo: Tipo, transacaoItem: TransacaoItem) {
        val categoriasRetornadas = context.resources.getStringArray(getCategoriasPeloTipo(tipo))
        val posicaoCategoria = categoriasRetornadas.indexOf(transacaoItem.categoria)
        campoCategoria.setSelection(posicaoCategoria, true)
    }

    private fun inicializaCampoData(transacaoItem: TransacaoItem) {
        campoData.setText(transacaoItem.data.formataParaDataBrasil())
    }

    private fun inicializaCampoValor(transacaoItem: TransacaoItem) {
        campoValor.setText(transacaoItem.valor.toString())
    }
}