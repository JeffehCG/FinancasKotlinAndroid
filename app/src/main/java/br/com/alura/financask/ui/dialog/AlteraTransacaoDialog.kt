package br.com.alura.financask.ui.dialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import br.com.alura.financask.R
import br.com.alura.financask.delegate.TrasacaoDelegate
import br.com.alura.financask.extesion.converteParaCalendar
import br.com.alura.financask.extesion.formataParaDataBrasil
import br.com.alura.financask.model.Tipo
import br.com.alura.financask.model.TransacaoItem
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*

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
    fun chamaDialog(transacaoItem: TransacaoItem, transacaoDelegate: TrasacaoDelegate) {

        val tipo = transacaoItem.tipo

        super.chamaDialog(tipo, transacaoDelegate) //super é usado quando metodos da classe pai não chamados

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