package br.com.alura.financask.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.alura.financask.R
import br.com.alura.financask.model.Tipo
import br.com.alura.financask.model.TransacaoItem
import br.com.alura.financask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.* //Com esse import do kotlin não é mais preciso ficar procurando os componentes da view pelo id // em vez de usar um findViewById e colocado o id direto
import java.math.BigDecimal
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() { //: é equivalente ao extends - para pegar determinada classe como herença

    //Codigo a serem criados na criação da activity (caso duvidas, analisar ciclo de vida activity)
    override fun onCreate(savedInstanceState: Bundle?) : Unit { //Onde esta Unit é o retorno da função, que não é obrigatorio
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes : List<TransacaoItem> = transacoesDeExemplo()

        configuraLista(transacoes)
    }

    private fun configuraLista(transacoes: List<TransacaoItem>) {
        //Colocando o ID do componente da view direto (sem precisar usar findViewById
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)//Usando o adapter para transformar os itens da List, e retornar uma view(transacao_item) para cada item da lista
    }

    private fun transacoesDeExemplo(): List<TransacaoItem> {
        //listOf - metodo para gerar uma lista (Array) (val = constante - não muda de valor ; var = mutavel)
        return listOf(
                //Passando o nome do parametro do construtor
                TransacaoItem(
                        valor = BigDecimal(20.5),
                        tipo = Tipo.DESPESA), //Cada item da lista sera uma instancia da classe
                TransacaoItem(
                        BigDecimal(100.0),
                        "Economia",
                        Tipo.RECEITA),
                TransacaoItem(
                        valor = BigDecimal(200.0),
                        categoria = "Casa",
                        tipo = Tipo.DESPESA),
                TransacaoItem(
                        valor = BigDecimal(500.0),
                        categoria = "Premio",
                        tipo = Tipo.RECEITA),
                TransacaoItem(
                        valor = BigDecimal(50.0),
                        categoria = "Almoço de final de semana",
                        tipo = Tipo.DESPESA)
        )
    }

}

//gerando arrayAdapter para colocar na lista (É preciso ele, pois a lista tera quantidades de dados diferentes, e a lista precisa se adaptar a esse array)
//val arrayAdapter = ArrayAdapter(this,
//        android.R.layout.simple_list_item_1, transacoes)