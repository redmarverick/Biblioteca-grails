package biblioteca.grails

class Autor {
    String nomeCompleto
    String biografia

    static constraints = {
        nomeCompleto blank: false, unique: true
        biografia nullable: true
    }
}