package biblioteca.grails

import grails.gorm.annotation.Entity

@Entity
@javax.persistence.NamedQuery(name = 'Locacao.findAllByCliente', query = 'FROM Locacao WHERE cliente = :cliente ORDER BY dataEmprestimo DESC')
class Locacao {
    Cliente cliente
    Livro livro
    Date dataEmprestimo
    Date dataDevolucaoPrevista
    Date diaDevolucao
    LocacaoSituacaoEnum situacao = LocacaoSituacaoEnum.Emprestado
    String prazo

    static belongsTo = [cliente: Cliente, livro: Livro]

    static constraints = {
        diaDevolucao(nullable: true)
        prazo(nullable: true)
        situacao enum: LocacaoSituacaoEnum
    }
}
