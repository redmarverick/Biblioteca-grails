package biblioteca.grails

class Autor {
    String nomeCompleto
    String biografia

    static constraints = {
        nomeCompleto blank: false
    }

    def getLivrosEscritos() {
        Livro.findAllByAutor(this)
    }
}
