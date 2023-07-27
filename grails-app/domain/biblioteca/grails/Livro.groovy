package biblioteca.grails

class Livro {
    String titulo
    String editora
    String anoPublicacao
    String isbn
    Integer exemplaresDisponiveis
    Autor autor

    static belongsTo = [autor: Autor]

    String getDisplayTitulo() {
        titulo
    }

    static constraints = {
        titulo blank: false, unique: true
        autor blank: false
        editora blank: false
        anoPublicacao blank: false
        isbn blank: false, unique: true
        exemplaresDisponiveis min: 0
    }

}
