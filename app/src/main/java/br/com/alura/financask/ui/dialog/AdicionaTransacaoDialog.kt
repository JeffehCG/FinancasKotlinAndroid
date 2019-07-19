package br.com.alura.financask.ui.dialog

import android.content.Context
import android.view.ViewGroup
import br.com.alura.financask.R
import br.com.alura.financask.model.Tipo

//Classe Filha, pegando como herença FormularioTransacaoDialog
class AdicionaTransacaoDialog(viewGroup: ViewGroup,
                              context: Context) : FormularioTransacaoDialog(viewGroup, context) {

    //Classes filhas devem obrigatoriamente chamar as classes abstratas (override) da classe pai
    override val tituloBotaoPositivo: String
        get() = "Adicionar"

    override fun getTituloPeloTipo(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.string.adiciona_receita
        } else {
            return R.string.adiciona_despesa
        }
    }

}

//alt + shift + ctrl + t = gerando classe super (pai) para fazer herença