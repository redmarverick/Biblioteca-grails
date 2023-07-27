package biblioteca.grails

class Locacao {
    Cliente cliente
    Livro livro
    Date dataEmprestimo
    Date dataDevolucaoPrevista
    Date diaDevolucao
    LocacaoSituacaoEnum situacao = LocacaoSituacaoEnum.Emprestado

    static belongsTo = [cliente: Cliente, livro: Livro]

    static constraints = {
        diaDevolucao(nullable: true)
        situacao enum: LocacaoSituacaoEnum
    }
}
