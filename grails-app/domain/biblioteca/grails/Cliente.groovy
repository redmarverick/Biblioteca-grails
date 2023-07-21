package biblioteca.grails

class Cliente {
    String nomeCompleto
    String endereco
    String telefone
    String email

    static constraints = {
        nomeCompleto blank: false, size: 3..50
        endereco blank: false, size: 3..50
        telefone blank: false, size: 3..50
        email blank: false, size: 3..50
    }
}
