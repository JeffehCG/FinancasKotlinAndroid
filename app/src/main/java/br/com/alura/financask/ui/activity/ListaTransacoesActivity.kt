package br.com.alura.financask.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import br.com.alura.financask.R
import br.com.alura.financask.delegate.TrasacaoDelegate
import br.com.alura.financask.model.Tipo
import br.com.alura.financask.model.TransacaoItem
import br.com.alura.financask.ui.ResumoView
import br.com.alura.financask.ui.adapter.ListaTransacoesAdapter
import br.com.alura.financask.ui.dialog.AdicionaTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() { //: é equivalente ao extends - para pegar determinada classe como herença

    //Lista de transações
    private val transacoes: MutableList<TransacaoItem> = mutableListOf()//listOf - metodo para gerar uma lista (Array) (val = constante - não muda de valor ; var = mutavel)

    //Codigo a serem criados na criação da activity (caso duvidas, analisar ciclo de vida activity)
    override fun onCreate(savedInstanceState: Bundle?) : Unit { //Onde esta Unit é o retorno da função, que não é obrigatorio
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

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
        AdicionaTransacaoDialog(window.decorView as ViewGroup, this) //Passando a view pai, e o contexto
                .chamaDialog(tipo, object : TrasacaoDelegate { //No delegate é passado as funções que seram executadas em determinada hora na classe
                    override fun delegate(transacao: TransacaoItem) {

                        atualizaTransacoes(transacao)

                        //Fechando botão
                        lista_transacoes_adiciona_menu.close(true)
                    }

                })
    }

    //Metodo que atualiza transações apos serem adicionadas mais
    private fun atualizaTransacoes(transacao: TransacaoItem) {
        transacoes.add(transacao)
        configuraLista()
        configuraResumo()
    }

    //Metodo que atualiza os valores do resumo
    private fun configuraResumo() {
        val view = window.decorView //Pegando a view dessa activity
        //Instanciando o resumoView e calculando seus valores
        val resumoView = ResumoView(this,view,transacoes)
                resumoView.atualizaResumo()
    }

    //Metodo que exibe as transações da lista
    private fun configuraLista() {
        //Colocando o ID do componente da view direto (sem precisar usar findViewById
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)//Usando o adapter para transformar os itens da List, e retornar uma view(transacao_item) para cada item da lista
    }
}

//gerando arrayAdapter para colocar na lista (É preciso ele, pois a lista tera quantidades de dados diferentes, e a lista precisa se adaptar a esse array)
//val arrayAdapter = ArrayAdapter(this,
//        android.R.layout.simple_list_item_1, transacoes)