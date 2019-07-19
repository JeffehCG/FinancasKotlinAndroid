package br.com.alura.financask.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import br.com.alura.financask.R
import br.com.alura.financask.delegate.TrasacaoDelegate
import br.com.alura.financask.model.Tipo
import br.com.alura.financask.model.TransacaoItem
import br.com.alura.financask.ui.ResumoView
import br.com.alura.financask.ui.adapter.ListaTransacoesAdapter
import br.com.alura.financask.ui.dialog.AdicionaTransacaoDialog
import br.com.alura.financask.ui.dialog.AlteraTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() { //: é equivalente ao extends - para pegar determinada classe como herença

    //Lista de transações
    private val transacoes: MutableList<TransacaoItem> = mutableListOf()//listOf - metodo para gerar uma lista (Array) (val = constante - não muda de valor ; var = mutavel)

    //Maneiras de trabalhar com variaveis nulas
    private val viewDaActivity: View by lazy {
        Log.i("teste lazy 0", "Inicialização do lazy")
        window.decorView // A atribuição só é realizada quando a variavel é chamada
    }
    private val viewGroupDaActivity by lazy {
        viewDaActivity as ViewGroup
    }
//    private lateinit var viewDaActivity: View //lateinit - defini que sera atribuido valor a variavel posteriormente
    //private var viewDaActivity: View? = null // ? - significa que a variavel pode receber nullo

    //Codigo a serem criados na criação da activity (caso duvidas, analisar ciclo de vida activity)
    override fun onCreate(savedInstanceState: Bundle?): Unit { //Onde esta Unit é o retorno da função, que não é obrigatorio
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

//        viewDaActivity = window.decorView // inicializando variavel quando esta usando lateinit ou ?


        //Testando log no console
        Log.i("teste lazy 1", viewDaActivity.toString())
        Log.i("teste lazy 2", viewDaActivity.toString())
        Log.i("teste lazy 3", viewDaActivity.toString())

        //Adicionando os valores do resumo
        configuraResumo()
        //Exibindo lista de transacoes na view
        configuraLista()
        //Tratando o clique dos botões para gerar nova transação
        configuraBotoes()
    }

    //metodo para tratar os cliques do botão
    private fun configuraBotoes() {
        //Receita
        lista_transacoes_adiciona_receita.setOnClickListener {
            //Criando a view do Alert
            chamaDialogDeAdicao(Tipo.RECEITA)
        }

        //Despesa
        lista_transacoes_adiciona_despesa.setOnClickListener {

            //Criando a view do Alert
            chamaDialogDeAdicao(Tipo.DESPESA)
        }
    }

    //Metodo para chamar o Alert de adição da transação
    private fun chamaDialogDeAdicao(tipo: Tipo) {
        AdicionaTransacaoDialog(viewGroupDaActivity, this) //Passando a view pai, e o contexto
                .chamaDialog(tipo, object : TrasacaoDelegate { //No delegate é passado as funções que seram executadas em determinada hora na classe
                    override fun delegate(transacao: TransacaoItem) {

                        //Adicionando nova transação a lista
                        adicionaTransacao(transacao)

                        //Fechando botão
                        lista_transacoes_adiciona_menu.close(true)
                    }

                })
    }

    private fun adicionaTransacao(transacao: TransacaoItem) {
        transacoes.add(transacao)
        atualizaTransacoes()
    }

    //Metodo que atualiza transações apos serem adicionadas mais
    private fun atualizaTransacoes() {
        configuraLista()
        configuraResumo()
    }

    //Metodo que atualiza os valores do resumo
    private fun configuraResumo() {

        //Instanciando o resumoView e calculando seus valores
        val resumoView = ResumoView(this, viewDaActivity, transacoes)
        resumoView.atualizaResumo()
    }

    //Metodo que exibe as transações da lista
    private fun configuraLista() {
        with(lista_transacoes_listview) {
            //Colocando o ID do componente da view direto (sem precisar usar findViewById
            adapter = ListaTransacoesAdapter(transacoes, this@ListaTransacoesActivity)//Usando o adapter para transformar os itens da List, e retornar uma view(transacao_item) para cada item da lista

            //Tratando o clique do usuario no item da transação (para efetuar alterações)
            setOnItemClickListener { viewGroup, view, posicao, id ->
                val transacao = transacoes[posicao] //Pegando a transação clicada, pela posicao dela
                chamaDialogDeAlteracao(transacao, posicao)
            }
        }
    }

    private fun chamaDialogDeAlteracao(transacao: TransacaoItem, posicao: Int) {
        AlteraTransacaoDialog(viewGroupDaActivity, this) //Chamando a classe para efetuar a alteração e exibir o alert
                .chamaDialog(transacao, object : TrasacaoDelegate {
                    override fun delegate(transacao: TransacaoItem) {
                        alterandoTransacao(posicao, transacao) //Alterando transação da lista
                    }

                })
    }

    private fun alterandoTransacao(posicao: Int, transacao: TransacaoItem) {
        transacoes[posicao] = transacao //Substituindo o item existente pelo item alterado, recebido pelo delegate
        atualizaTransacoes()
    }
}

//gerando arrayAdapter para colocar na lista (É preciso ele, pois a lista tera quantidades de dados diferentes, e a lista precisa se adaptar a esse array)
//val arrayAdapter = ArrayAdapter(this,
//        android.R.layout.simple_list_item_1, transacoes)