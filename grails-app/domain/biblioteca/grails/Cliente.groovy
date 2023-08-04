package biblioteca.grails

import java.util.Date
import java.util.UUID

class Cliente {
    UUID clienteId
    String nomeCompleto
    String endereco
    String telefone
    String email
    Integer locacoesCount

    static hasMany = [locacoes: Locacao]

    static constraints = {
        nomeCompleto blank: false, size: 3..50
        endereco blank: false, size: 3..50
        telefone blank: false, size: 3..50
        email blank: false, size: 3..50
        clienteId nullable: true
        locacoesCount nullable: true
    }

    def beforeUpdate() {
        locacoesCount = locacoes?.size() ?: 0
    }

    def beforeInsert() {
        if (!clienteId) {
            clienteId = UUID.randomUUID()
        }
    }
}
