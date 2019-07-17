package br.com.alura.financask.extesion

fun String.limitaEmAte(caracteres: Int) : String{
    //Formatando nome da categoria, caso seja muito grande
    if(this.length > caracteres){
        return "${this.substring(0, caracteres)}..." //pegando do primeiro caracter até o desejado(caracteres)
    }
    return this
}
