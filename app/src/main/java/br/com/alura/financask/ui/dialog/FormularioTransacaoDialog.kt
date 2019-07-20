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
import br.com.alura.financask.delegate.TransacaoDelegate
import br.com.alura.financask.extesion.converteParaCalendar
import br.com.alura.financask.extesion.formataParaDataBrasil
import br.com.alura.financask.model.Tipo
import br.com.alura.financask.model.TransacaoItem
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*
//Classe Pai (É preciso usar open na classe pai, ou abstract)
//abstract é utilizado em classes que conteram metodos abstratos, que seram utilizados de maneiras distintas por classes filhas
abstract class FormularioTransacaoDialog (private val viewGroup: ViewGroup,
                                      private val context: Context) {
    //Criando View
    private val viewCriadaAddReceita = criaLayout()
    //Campos da View (Quando é usado protected apenas classes do mesmo pacote que estão implementando essa classe como herença podem usar
    protected val campoValor = viewCriadaAddReceita.form_transacao_valor
    protected val campoData = viewCriadaAddReceita.form_transacao_data
    protected val campoCategoria = viewCriadaAddReceita.form_transacao_categoria
    abstract protected val tituloBotaoPositivo : String //Propriedade abstract

    fun chamaDialog(tipo: Tipo, delegate: (transacao: TransacaoItem) -> Unit) { //Recebendo delegate para efetuar a atualização da lista de transações

        //Alterando valores da View
        configuraCampoData()
        configuraCampoCategoria(tipo)

        //Criando o Alert (Tela que aparece a apertar o botão)
        configuraFormulario(tipo, delegate)
    }

    private fun configuraFormulario(tipo: Tipo, delegate: (transacao: TransacaoItem) -> Unit) {

        val titulo = getTituloPeloTipo(tipo)

        AlertDialog.Builder(context)//Colocando alert quando é clicado no botão
                .setTitle(titulo)
                .setView(viewCriadaAddReceita) //Adicionando view criada ao alert
                .setPositiveButton(tituloBotaoPositivo, //Adicionando botão inserir transação
                        { dialogInterface, i ->

                            //Pegando os valores da transação
                            val valorTexto = campoValor.text.toString()
                            val dataTexto = campoData.text.toString()
                            val categoriaTexto = campoCategoria.selectedItem.toString()

                            //Convertendo o valor
                            var valor = converteCampoValor(valorTexto)

                            //Convertendo a data
                            val data = dataTexto.converteParaCalendar()

                            //Adicionando transação a lista
                            val transacaoCriada = TransacaoItem(tipo = tipo,
                                    valor = valor,
                                    data = data,
                                    categoria = categoriaTexto)

                            //O Delegate tem a função de uma classe conseguir se comunicar com as funções de uma activity
                            // Nesse caso, para conseguir adicionar a transação a lista, e fechar o botão)
                            //São passadas a as funções da activity dentro do delegate, para elas serem chamadas aqui na classe
                            delegate(transacaoCriada)

                        })
                .setNegativeButton("Cancelar", null)
                .show()
    }

    //Classe abstrata = é utilizada de maneiras diferentes por classes filhas (não pode ser private, apenas protected ou public)
    abstract protected fun getTituloPeloTipo(tipo: Tipo): Int


    private fun converteCampoValor(valorTexto: String): BigDecimal {
        var valor = try {
            BigDecimal(valorTexto)
        } catch (exception: NumberFormatException) {
            Toast.makeText(context, "Falha na conversão de valor", Toast.LENGTH_LONG).show()
            BigDecimal.ZERO
        }
        return valor
    }

    private fun configuraCampoCategoria(tipo: Tipo) {

        //Passando as categorias de acordo com o tipo da transação
        val categorias = getCategoriasPeloTipo(tipo)

        val adapter = ArrayAdapter.createFromResource(
                context,
                categorias,
                android.R.layout.simple_spinner_dropdown_item)
        campoCategoria.adapter = adapter //Para setar as categorias é passado um array
    }

    protected fun getCategoriasPeloTipo(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.array.categorias_de_receita
        } else {
            return R.array.categorias_de_despesa
        }
    }

    private fun configuraCampoData() {

        val hoje = Calendar.getInstance() //Data atual
        val ano = hoje.get(Calendar.YEAR)
        val mes = hoje.get(Calendar.MONTH)
        val dia = hoje.get(Calendar.DAY_OF_MONTH)

        campoData.setText(hoje.formataParaDataBrasil()) //Setando primeiramente a data atual
        //Lidando com o clique no campo data
        campoData.setOnClickListener {
            //Clicando no campo na data aparece o calendario
            DatePickerDialog(context,
                    DatePickerDialog.OnDateSetListener { view, ano, mes, dia ->
                        // _ = quando o campo não esta sendo usado (view)
                        val dataSelecionada = Calendar.getInstance()
                        dataSelecionada.set(ano, mes, dia)
                        campoData.setText(dataSelecionada.formataParaDataBrasil())
                    },
                    ano, mes, dia)
                    .show()
        }
    }

    private fun criaLayout(): View {
        return LayoutInflater.from(context).inflate(
                R.layout.form_transacao,
                viewGroup, //Para gerar o layout é preciso colocar a view pai (nesse caso a listaTransacoes)
                false)
    }
}