package biblioteca.grails

import java.util.Date
import java.util.UUID

class Locacao {
    Cliente cliente
    Livro livro
    Date dataEmprestimo
    Date dataDevolucaoPrevista
    Date diaDevolucao
    LocacaoSituacaoEnum situacao = LocacaoSituacaoEnum.Emprestado
    String prazo

    // Define the many-to-one association with Cliente
    static belongsTo = [cliente: Cliente]

    static constraints = {
        diaDevolucao(nullable: true)
        prazo(nullable: true)
        situacao enum: LocacaoSituacaoEnum
    }
    static namedQueries = {
        locacoesByClienteId { clienteNome ->
            createAlias('cliente', 'c')
            eq('c.nomeCompleto', clienteNome)
        }
    }

}
