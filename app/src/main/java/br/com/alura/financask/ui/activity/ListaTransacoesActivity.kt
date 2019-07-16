package br.com.alura.financask.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import br.com.alura.financask.R
import br.com.alura.financask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.* //Com esse import do kotlin não é mais preciso ficar procurando o componente da view pelo id
                                                                    // em vez de usar um findViewById e colocado o id direto

class ListaTransacoesActivity : AppCompatActivity() { //: é equivalente ao extends - para pegar determinada classe como herença

    //Codigo a serem criados na criação da activity (caso duvidas, analisar ciclo de vida activity)
    override fun onCreate(savedInstanceState: Bundle?) : Unit { //Onde esta Unit é o retorno da função, que não é obrigatorio
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        //listOf - metodo para gerar uma lista (Array) (val = constante - não muda de valor )
        val transacoes = listOf("Comida - R$ 20,50",
                "Economia - R$ 100,00")

        val arrayAdapter = ArrayAdapter(this,
                android.R.layout.simple_list_item_1, transacoes)

        //Colocando o ID do componente da view direto (sem precisar usar findViewById
        lista_transacoes_listview.setAdapter(
                ListaTransacoesAdapter(transacoes, this))
    }

}